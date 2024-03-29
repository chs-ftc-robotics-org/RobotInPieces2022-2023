package org.RIP.subsystems;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;
import org.RIP.Subsystem;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

public class ConeWebcam extends Subsystem {
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final String VUFORIA_KEY = "AXyOBAb/////AAABmR6fZcY51EWEsPmbfWJ1w99ml2AnaVfhpovIbujIbr0CK66LMFHd5kpdeX/Z776lrYYfpu3LXAgDw0ZRpYnRuMwVrPtHJ12i95kzVFtN023RjzPCMPbkYmFlSXhSjm2Pz5H4vtnqhvxcbbEvvklIi1LQIjhzxdI5Ue5M5MdkbwDbGwFdQG86jS3BsJTwoXC1Citcnzih9rmBEudWy3bZUBa6osfNK70T3KEoEWrOp/hKBzw+K0D7uwx3Rhqu+yZcM+nLizyKEv6BiMGRjEL3le0P67bGfBnOnxbYSKY+4ifFA2k7cUoZbuTTYVzNbtkTf4aBcn55ltcU90QtAqzRStLHC4ij3JSdtW3dqLJoMGWB";

    private static final String TFOD_MODEL_ASSET = "rip_model_v2.tflite";
    //this is where it's stored on the control hub
    private static final String TFOD_MODEL_FILE  = Environment.getExternalStorageDirectory().getPath() + "/FIRST/tflitemodels/rip_model_v1.tflite";
    private static final String[] LABELS = {
            "Coffin",
            "Gear",
            "Skull"
    };

//    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
//    private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/CustomTeamModel.tflite";
//    /** Position 1: skulls, position 2: gears, position 3; coffins */
//    private static final String[] LABELS = {
//            "1 Bolt",
//            "2 Bulb",
//            "3 Panel"
//    };


    private LinearOpMode opMode;
    private ElapsedTime globalTimer;
    private List<Recognition> allRecognitions = new ArrayList<Recognition>();
    @Override
    public void disable() {
        tfod.deactivate();

    }

    @Override
    public void initialize(LinearOpMode opMode, ElapsedTime globalTimer) {
        this.opMode = opMode;
        this.globalTimer = globalTimer;
            /*
             * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
             */
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

        /**
         * Initialize the TensorFlow Object Detection engine.
         */
            int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfodParameters.minResultConfidence = 0.75f;
            tfodParameters.isModelTensorFlow2 = true;
            tfodParameters.inputSize = 300;
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

            // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
            // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
            // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
            if (tfod != null) {
                tfod.activate();

                // The TensorFlow software will scale the input images from the camera to a lower resolution.
                // This can result in lower detection accuracy at longer distances (> 55cm or 22").
                // If your target is at distance greater than 50 cm (20") you can increase the magnification value
                // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
                // should be set to the value of the images used to create the TensorFlow Object Detection model
                // (typically 16/9).
                tfod.setZoom(1.0, 16.0/9.0);
            }
        }


    @Override
    public void update() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                allRecognitions.addAll(updatedRecognitions);
                //opMode.telemetry.addData("# Objects Detected", updatedRecognitions.size());

                // step through the list of recognitions and display image position/size information for each one
                // Note: "Image number" refers to the randomized image orientation/number
//                for (Recognition recognition : updatedRecognitions) {
//                    double col = (recognition.getLeft() + recognition.getRight()) / 2;
//                    double row = (recognition.getTop() + recognition.getBottom()) / 2;
//                    double width = Math.abs(recognition.getRight() - recognition.getLeft());
//                    double height = Math.abs(recognition.getTop() - recognition.getBottom());
//
//                    opMode.telemetry.addData("", " ");
//                    opMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
//                    opMode.telemetry.addData("- Position (Row/Col)", "%.0f / %.0f", row, col);
//                    opMode.telemetry.addData("- Size (Width/Height)", "%.0f / %.0f", width, height);
//                }
                opMode.telemetry.update();
            }
        }
    }

    public void stop() {

    }
    public int getPositionFromCamera() {
        //Find the recognition type most frequently used
        int[]frequency = new int[LABELS.length];
        for(Recognition recognition: allRecognitions) {
            for(int i=0; i<LABELS.length; i++){
                if (recognition.getLabel().equals(LABELS[i])) {
                    frequency[i]++;
                }
            }
        }
        int highestFrequency = 0;
        int highestFrequencyIndex = 0;
        frequency[1]+=3;
        for(int i=0; i<LABELS.length; i++){
            opMode.telemetry.addLine("freq1:"+frequency[0]+",freq2:"+frequency[1]+",freq3:"+frequency[2]);
            if (frequency[i] > highestFrequency){
                highestFrequency = frequency[i];
                highestFrequencyIndex = i;
            }
        }
        return highestFrequencyIndex+1;
    }

    @Override
    public String toString() {
        return "ConeWebcam";
    }
}
