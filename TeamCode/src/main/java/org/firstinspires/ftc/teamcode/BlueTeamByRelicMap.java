package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "BlueTeamByRelic", group = "Autonomous")


public class BlueTeamByRelicMap extends LinearOpMode {
    Hardware robot = new Hardware();

    VuforiaLocalizer vuforia;

    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AfmBbcz/////AAAAGbLGg++zzk4MiOrcPTc3t9xQj3QHfISJprebOgt5JJ4+83xtFO+ApGlI3GVY/aMgCpoGEIzaJse9sXiYDiLYpJQlGDX765tWJUrqM+pzqLxVXjWA1J6c968/YqYq74Vq5emNxGHj5SF3HP3m43Iq/YYgkSdMv4BR+RThPPnIIzrbAjEAHHtMgH7vVh036+bcw9UqBfSdD/IBqrKpJLERn5+Qi/4Q4EoReCC0CTDfZ+LcY0rUur0QZRkMpxx/9s4eCgIU+qfOcSlBvjoX7QAQ2MImUME1y5yJiyaWueamnhRBOwERGBuDKyGp4eBWp4i3esJcplrWYovjzPg9fL7Thy8v9KnrHy22PUFAYY+1vjKp";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        this.vuforia = ClassFactory.createVuforiaLocalizer(params);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        robot.init(hardwareMap);
        waitForStart();

        robot.jewelExtend.setPosition(.5);

        relicTrackables.activate();
        //activate balls here
        int column = 1;
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if(vuMark != RelicRecoveryVuMark.UNKNOWN){
            if(vuMark == RelicRecoveryVuMark.LEFT){
                column = 0;
            }else if(vuMark == RelicRecoveryVuMark.RIGHT){
                column = 2;
            }else{
                column = 1;
            }
        }



        //vuforia business here for sensing the color of the ball
        robot.initialPulleyRaiser.setPower(1);  //lifts baby clips to clip onto sides
        Thread.sleep(100);
        robot.initialPulleyRaiser.setPower(0);  //stops clips and platform drops
        robot.bottomClaw.setPosition(0);  //grips the block infront

        goRight();  //moves to jewel
        Thread.sleep(1000);
        robot.jewelExtend.setPosition(0);
        //if then statement to turn left/right and back to pop off jewel
        if(column == 0) {
            Thread.sleep(1000);
        }else if (column == 1) {
            Thread.sleep(1100);
        }else {
            Thread.sleep(1200);
        }

        goForward();
        Thread.sleep(100);
        robot.bottomClaw.setPosition(.5);
        Thread.sleep(100);
        goBackward();
        Thread.sleep(100);
        goStop();


        //Need to first put jewel servo down between balls to sense it. Then turn right/left depending on which one and back, go forward and place block into cryptobox

    }

    public void goStop()  {
        robot.frontLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.backLeftMotor.setPower(0);
        robot.backRightMotor.setPower(0);
    }

    public void goForward() {

        robot.frontLeftMotor.setPower(1);
        robot.frontRightMotor.setPower(1);
        robot.backLeftMotor.setPower(1);
        robot.backRightMotor.setPower(1);
    }

    public void goBackward() {

        robot.frontLeftMotor.setPower(-1);
        robot.frontRightMotor.setPower(-1);
        robot.backLeftMotor.setPower(-1);
        robot.backRightMotor.setPower(-1);

    }

    public void goRight() {
        robot.frontLeftMotor.setPower(-1);
        robot.frontRightMotor.setPower(1);
        robot.backLeftMotor.setPower(-1);
        robot.backRightMotor.setPower(1);

    }

    public void goLeft() {
        robot.frontLeftMotor.setPower(1);
        robot.frontRightMotor.setPower(-1);
        robot.backLeftMotor.setPower(1);
        robot.backRightMotor.setPower(-1);

    }

}