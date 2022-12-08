package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@TeleOp(name="TeleOp: Test", group="Tests")
public class TeleOpTest extends LinearOpMode {

    //Declaring motors here
    DcMotor left;
    DcMotor right;

    //When the "INIT" button is pressed:
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final String VUFORIA_KEY = "AXyOBAb/////AAABmR6fZcY51EWEsPmbfWJ1w99ml2AnaVfhpovIbujIbr0CK66LMFHd5kpdeX/Z776lrYYfpu3LXAgDw0ZRpYnRuMwVrPtHJ12i95kzVFtN023RjzPCMPbkYmFlSXhSjm2Pz5H4vtnqhvxcbbEvvklIi1LQIjhzxdI5Ue5M5MdkbwDbGwFdQG86jS3BsJTwoXC1Citcnzih9rmBEudWy3bZUBa6osfNK70T3KEoEWrOp/hKBzw+K0D7uwx3Rhqu+yZcM+nLizyKEv6BiMGRjEL3le0P67bGfBnOnxbYSKY+4ifFA2k7cUoZbuTTYVzNbtkTf4aBcn55ltcU90QtAqzRStLHC4ij3JSdtW3dqLJoMGWB";


    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */

    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    // private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/CustomTeamModel.tflite";


    private static final String[] LABELS = {
            "1 Bolt",
            "2 Bulb",
            "3 Panel"
    };
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }


    @Override
    public void runOpMode() {
        initVuforia();
        initTfod();

        //Get motors from Hardware Map
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor backRight = hardwareMap.get(DcMotor.class, "back_right");

        //Wait here until the "START" button is pressed
        waitForStart();

        double x;
        double y;
        double y1;
        double x1;
        double strife;
        //Loop this until the "STOP" button is pressed
        while (opModeIsActive()) {
            // Forward & Backward
            y = (-gamepad1.left_stick_y) * 0.2;
            x1 = -(gamepad1.left_stick_x) * 0.2;
            x = (-gamepad1.right_stick_x) * 0.2;

            frontLeft.setPower((y-x)-x1);
            backLeft.setPower((y-x)+x1);
            frontRight.setPower((y+x)+x1);
            backRight.setPower((y+x)-x1);
        }
    }
}