package org.firstinspires.ftc.teamcode;

import android.view.Display;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
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
//    DcMotor cannonMotor = null;
//    DcMotor lift = null;

   // ColorSensor beaconSensor = null;
    ModernRoboticsI2cGyro gyro = null;

    VuforiaLocalizer vuforia = null;
    VuforiaTrackables ciphers = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    static final int tickSpeed = 1120, distanceFromCentermm = 203;
    static final double wheelCircumferencemm = 159.5929;




    public Hardware() {
    }

    public void init(HardwareMap ahwMap) throws InterruptedException{
        hwMap = ahwMap;

        InitComponents();
//        InitVuforia();
    }


    private void InitVuforia() {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AfmBbcz/////AAAAGbLGg++zzk4MiOrcPTc3t9xQj3QHfISJprebOgt5JJ4+83xtFO+ApGlI3GVY/aMgCpoGEIzaJse9sXiYDiLYpJQlGDX765tWJUrqM+pzqLxVXjWA1J6c968/YqYq74Vq5emNxGHj5SF3HP3m43Iq/YYgkSdMv4BR+RThPPnIIzrbAjEAHHtMgH7vVh036+bcw9UqBfSdD/IBqrKpJLERn5+Qi/4Q4EoReCC0CTDfZ+LcY0rUur0QZRkMpxx/9s4eCgIU+qfOcSlBvjoX7QAQ2MImUME1y5yJiyaWueamnhRBOwERGBuDKyGp4eBWp4i3esJcplrWYovjzPg9fL7Thy8v9KnrHy22PUFAYY+1vjKp";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 1);

        ciphers = vuforia.loadTrackablesFromAsset("RelicVuMark");

        ciphers.get(0).setName("RelicRecovery");


    }

    private void InitComponents() {

        GetMotors();

        InitMotorsDirection();

        SetMotorsInitPower();

        SetMotorInitMode();

        gyro = (ModernRoboticsI2cGyro)hwMap.gyroSensor.get("Gyro");

//        cannonMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    private void SetMotorInitMode() {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        cannonMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void SetMotorsInitPower() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
//        cannonMotor.setPower(0);
//        lift.setPower(0);
    }

    private void InitMotorsDirection() {
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE); // reversed
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
//        cannonMotor.setDirection(DcMotor.Direction.FORWARD);
//        lift.setDirection(DcMotor.Direction.REVERSE);
    }

    private void GetMotors() {
        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");
//        cannonMotor = hwMap.dcMotor.get("cannonMotor");
//        lift = hwMap.dcMotor.get("lift");
    }

    public void waitForTick(long periodMs) throws InterruptedException {

        long remaining = periodMs - (long) period.milliseconds();

        if (remaining > 0)
            Thread.sleep(remaining);

        period.reset();
    }
}
