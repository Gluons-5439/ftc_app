package org.firstinspires.ftc.teamcode;

import android.view.Display;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class Hardware{
    DcMotor frontLeftMotor = null;
    DcMotor frontRightMotor = null;
    DcMotor backLeftMotor = null;
    DcMotor backRightMotor = null;
    DcMotor liftMotorLeft = null;
    DcMotor liftMotorRight = null;
    DcMotor initialPulleyRaiser = null;

    Servo leftLift = null;
    Servo rightLift = null;
    Servo bottomClaw = null;
    Servo topClaw = null;
    Servo jewelExtend = null;
    Servo clawRaiser = null;



    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    static final int tickSpeed = 1120, distanceFromCentermm = 203;
    static final double wheelCircumferencemm = 159.5929;


    public Hardware() {
    }


    public void init(HardwareMap ahwMap) throws InterruptedException{
        hwMap = ahwMap;

        InitComponents();
        //InitVuforia();
    }





    private void InitComponents() {

        GetMotors();

        InitMotorsDirection();

        SetMotorsInitPower();

        SetMotorInitMode();
    }


    private void SetMotorInitMode() {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        initialPulleyRaiser.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }


    private void SetMotorsInitPower() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        liftMotorLeft.setPower(0);
        liftMotorRight.setPower(0);
        initialPulleyRaiser.setPower(0);

        leftLift.setPosition(0);
        rightLift.setPosition(0);
        bottomClaw.setPosition(0);
        topClaw.setPosition(0);
        jewelExtend.setPosition(0);
        clawRaiser.setPosition(0);
    }


    private void InitMotorsDirection() {
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE); // reversed
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotorLeft.setDirection(DcMotor.Direction.FORWARD);
        liftMotorRight.setDirection(DcMotor.Direction.FORWARD);
        initialPulleyRaiser.setDirection(DcMotor.Direction.FORWARD);

    }


    private void GetMotors() {
        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");
        liftMotorLeft = hwMap.dcMotor.get("liftMotorLeft");
        liftMotorRight = hwMap.dcMotor.get("liftMotorRight");
        initialPulleyRaiser = hwMap.dcMotor.get("initialPulleyRaiser");

        leftLift = hwMap.servo.get("leftLift");
        rightLift = hwMap.servo.get("rightLift");
        bottomClaw = hwMap.servo.get("bottomClaw");
        topClaw = hwMap.servo.get("topClaw");
        jewelExtend = hwMap.servo.get("jewelExtend");
        clawRaiser = hwMap.servo.get("clawRaiser");
    }


    public void waitForTick(long periodMs) throws InterruptedException {

        long remaining = periodMs - (long) period.milliseconds();

        if (remaining > 0)
            Thread.sleep(remaining);

        period.reset();
    }
}