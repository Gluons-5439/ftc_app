package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Setup Program", group = "TeleOp")

public class SetupProgram extends LinearOpMode {
    Hardware robot = new Hardware();

    double factor = 2;

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        //Upon initialization maps robot hardware

        waitForStart();


        double bottomBack = 0.5;

        while (opModeIsActive()) {
            //Creates variable theta which equals robot heading
            robot.bottomClaw.setPower(bottomBack);



            if(gamepad2.dpad_up){
                bottomBack -= 0.001;
            }else if(gamepad2.dpad_down){
                bottomBack += 0.001;
            }
/*
            if(gamepad2.b)  {
                robot.topClaw.setPower(1.0);
            }else if(gamepad2.a)   {
                robot.topClaw.setPower(-1.0);
            }else{
                robot.topClaw.setPower(0);
            }
            */

            factor = gamepad2.right_bumper ? 1 : 2;
            double padTwoLeftY = Math.abs(gamepad2.left_stick_y)>0.2 ? -gamepad2.left_stick_y/factor : 0;
            double padTwoRightY = Math.abs(gamepad2.right_stick_y)>0.2 ? -gamepad2.right_stick_y/factor : 0;
            //Deadzone for lift motors
            robot.liftMotorLeft.setPower(padTwoLeftY);
            robot.liftMotorRight.setPower(padTwoLeftY);
            robot.initialPulleyRaiser.setPower(padTwoRightY);
//            robot.bottomClaw.setPower(padTwoRightY);
            //Sets power for motors raising and lowering pulley equal to gamepad2 left stick
            telemetry.addData("Left:", robot.liftMotorLeft.getPower());
            telemetry.addData("Right: ", robot.liftMotorRight.getPower());

            telemetry.addData("Servo Claw:", robot.bottomClaw.getPower());

            telemetry.update();
            //Adds gyro heading to telemetry

            robot.waitForTick(40);
            //Stops phone from queuing too many commands and breaking
        }
    }
}