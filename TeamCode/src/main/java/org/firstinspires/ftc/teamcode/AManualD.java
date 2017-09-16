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
        boolean shootTog = false, spinTog = false;

        double left = Range.clip(gamepad1.left_stick_y, -1.0, 1.0);
        double right = Range.clip(gamepad1.right_stick_y, -1.0, 1.0);

        if (left <= .025 && right <= .025)
            dPad();
        else {
            motorPow(-left, -right);
        }

        if(gamepad1.b) spinTog = spinTog ? false:true;
        robot.cannonMotor.setPower(spinTog ? 1:0);

        if(gamepad1.right_bumper) shootTog = shootTog ? false:true;
        robot.cannonMotor.setPower(shootTog ? 1:0);



        if (gamepad1.right_trigger > 0)
            robot.lift.setPower(1.0);
        else if (gamepad1.left_trigger > 0)
            robot.lift.setPower(-1.0);
        else
            robot.lift.setPower(0);

        robot.waitForTick(40);
    }

    private void dPad() {
        if (gamepad1.dpad_up) motorPow(0.5,0.5);
        else if (gamepad1.dpad_down) motorPow(-0.5,-0.5);
        else motorPow(0,0);
    }

    private void motorPow(double left, double right) {
        robot.frontLeftMotor.setPower(left);
        robot.backLeftMotor.setPower(left);
        robot.frontRightMotor.setPower(right);
        robot.backRightMotor.setPower(right);
    }
}