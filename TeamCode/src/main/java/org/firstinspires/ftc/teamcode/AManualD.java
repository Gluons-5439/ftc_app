package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Linear Driver TeleOp", group = "TeleOp")

public class AManualD extends LinearOpMode {
    Hardware robot = new Hardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            control();
        }
    }

    public void control() throws java.lang.InterruptedException {
        boolean  spinTog = false;

        double left = Range.clip(gamepad1.left_stick_y, -1.0, 1.0);
        double right = Range.clip(gamepad1.right_stick_y, -1.0, 1.0);

        if(gamepad1.left_bumper){
            throw new NullPointerException();
        }

        motorPow(left, right);


        if(gamepad1.right_bumper || gamepad2.right_bumper) spinTog = spinTog ? false:true;
        robot.cannonMotor.setPower(spinTog ? 1:0);


        if (gamepad1.right_trigger > 0 || gamepad2.right_trigger > 0)
            robot.lift.setPower(1.0);
        else if (gamepad1.left_trigger > 0 || gamepad2.left_trigger > 0)
            robot.lift.setPower(-1.0);
        else
            robot.lift.setPower(0);

        robot.waitForTick(40);
    }

    private void motorPow(double left, double right) {
        robot.frontLeftMotor.setPower(left);
        robot.backLeftMotor.setPower(left);
        robot.frontRightMotor.setPower(right);
        robot.backRightMotor.setPower(right);
    }
}