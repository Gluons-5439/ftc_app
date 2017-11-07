package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.util.*;


@Autonomous(name = "CompetitionAuto", group = "Autonomous")


public class Autonomous extends LinearOpMode {
    Hardware robot = new Hardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        //Need to first put jewel servo down between balls to sense it. Then turn right/left depending on which one and back, go forward and place block into cryptobox

    }

    public void goForward() {

        frontLeftMotor.setPower(1);
        frontRightMotor.setPower(1);
        backLeftMotor.setPower(1);
        backRightMotor.setPower(1);
    }

    public void goBackward() {

        frontLeftMotor.setPower(-1);
        frontRightMotor.setPower(-1);
        backLeftMotor.setPower(-1);
        backRightMotor.setPower(-1);

    }

    public void goRight() {
        frontLeftMotor.setPower(-1);
        frontRightMotor.setPower(1);
        backLeftMotor.setPower(-1);
        backRightMotor.setPower(1);

    }

    public void goLeft() {
        frontLeftMotor.setPower(1);
        frontRightMotor.setPower(-1);
        backLeftMotor.setPower(1);
        backRightMotor.setPower(-1);

    }

}
