package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by evanblanke on 11/13/17.
 */

public class AutonomousTools {
    Hardware robot = null;
    AutonomousTools(Hardware newRobot){
        Hardware robot = newRobot;
    }


//Change this
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    // These constants define the desired driving/control characteristics
    // The can/should be tweaked to suite the specific robot drive train.
    static final double     DRIVE_SPEED             = 0.7;     // Nominal speed for better accuracy.
    static final double     TURN_SPEED              = 0.5;     // Nominal half speed for better accuracy.

    static final double     HEADING_THRESHOLD       = 1 ;      // As tight as we can make it with an integer gyro
    static final double     P_TURN_COEFF            = 0.1;     // Larger is more responsive, but also less stable
    static final double     P_DRIVE_COEFF           = 0.15;


    public void initVuforia(VuforiaLocalizer vuforia, HardwareMap hardwareMap, VuforiaTrackables relicTrackables, VuforiaTrackable relicTemplate){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AfmBbcz/////AAAAGbLGg++zzk4MiOrcPTc3t9xQj3QHfISJprebOgt5JJ4+83xtFO+ApGlI3GVY/aMgCpoGEIzaJse9sXiYDiLYpJQlGDX765tWJUrqM+pzqLxVXjWA1J6c968/YqYq74Vq5emNxGHj5SF3HP3m43Iq/YYgkSdMv4BR+RThPPnIIzrbAjEAHHtMgH7vVh036+bcw9UqBfSdD/IBqrKpJLERn5+Qi/4Q4EoReCC0CTDfZ+LcY0rUur0QZRkMpxx/9s4eCgIU+qfOcSlBvjoX7QAQ2MImUME1y5yJiyaWueamnhRBOwERGBuDKyGp4eBWp4i3esJcplrWYovjzPg9fL7Thy8v9KnrHy22PUFAYY+1vjKp";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        vuforia = ClassFactory.createVuforiaLocalizer(params);

        relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
    }
    public void initPulleyRaiser() throws InterruptedException{
        //NOTE ALL NUMBERS ARE (slightly less) THEORETICAL... ONCE TESTING IS COMPLETED THESE NUMBERS WILL BE FINALIZED
        robot.initialPulleyRaiser.setPower(0.5);  //lifts baby clips to clip onto sides
        robot.liftMotorLeft.setPower(-0.5);
        robot.liftMotorRight.setPower(-0.5);
        while(robot.initialPulleyRaiser.getCurrentPosition() < 357){ Thread.sleep(40); } //Counts encoder values travels 9'' (9/C)*280
        robot.initialPulleyRaiser.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.initialPulleyRaiser.setPower(0);
        robot.liftMotorRight.setPower(0);
        robot.liftMotorLeft.setPower(0);
        robot.bottomClaw.setPower(0.5);
    }
    public void goStop()  {
        robot.frontLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.backLeftMotor.setPower(0);
        robot.backRightMotor.setPower(0);
    }

    public void goForward(double p) {

        robot.frontLeftMotor.setPower(p);
        robot.frontRightMotor.setPower(p);
        robot.backLeftMotor.setPower(p);
        robot.backRightMotor.setPower(p);
    }

    public void goBackward(double p) {

        robot.frontLeftMotor.setPower(-p);
        robot.frontRightMotor.setPower(-p);
        robot.backLeftMotor.setPower(-p);
        robot.backRightMotor.setPower(-p);

    }

    public void goRight(double p) {
        robot.frontLeftMotor.setPower(-p);
        robot.frontRightMotor.setPower(p);
        robot.backLeftMotor.setPower(-p);
        robot.backRightMotor.setPower(p);

    }

    public void goLeft(double p) {
        robot.frontLeftMotor.setPower(p);
        robot.frontRightMotor.setPower(-p);
        robot.backLeftMotor.setPower(p);
        robot.backRightMotor.setPower(-p);

    }

    public void goClockwise(double p) {
        robot.frontLeftMotor.setPower(p);
        robot.frontRightMotor.setPower(-p);
        robot.backLeftMotor.setPower(p);
        robot.backRightMotor.setPower(-p);

    }

    public void goCounterClockwise(double p) {
        robot.frontLeftMotor.setPower(p);
        robot.frontRightMotor.setPower(-p);
        robot.backLeftMotor.setPower(p);
        robot.backRightMotor.setPower(-p);

    }

    public void moveVector(double degree, double power){
        double clockwise = 0;
        double forward = 0;
        double right = 0;

        double rightDegree = Math.toDegrees(Math.sin(degree)*power);
        double forwardDegree = Math.toDegrees(Math.cos(degree)*power);
        right = Math.toRadians(degree);
        forward = Math.toRadians(degree);


        //clockwise *= -0.5;
        robot.frontLeftMotor.setPower(Range.clip(forward+clockwise+right,-1,1));
        robot.backLeftMotor.setPower(Range.clip(forward+clockwise-right,-1,1));
        robot.frontRightMotor.setPower(Range.clip(forward-clockwise-right,-1,1));
        robot.backRightMotor.setPower(Range.clip(forward-clockwise+right,-1,1));

    }
    /*
    public void gyroDrive ( double speed, double distance, double angle) {

        int     newLeftTarget;
        int     newRightTarget;
        int     moveCounts;
        double  max;
        double  error;
        double  steer;
        double  leftSpeed;
        double  rightSpeed;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            moveCounts = (int)(distance * COUNTS_PER_INCH);
            newLeftTarget = robot.backLeftMotor.getCurrentPosition() + moveCounts;
            newRightTarget = robot.backRightMotor.getCurrentPosition() + moveCounts;

            // Set Target and Turn On RUN_TO_POSITION
            robot.backLeftMotor.setTargetPosition(newLeftTarget);
            robot.backRightMotor.setTargetPosition(newRightTarget);

            robot.backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // start motion.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            robot.backLeftMotor.setPower(speed);
            robot.backRightMotor.setPower(speed);

            // keep looping while we are still active, and BOTH motors are running.
            while (opModeIsActive() &&
                    (robot.backLeftMotor.isBusy() && robot.backRightMotor.isBusy())) {

                // adjust relative speed based on heading error.
                error = getError(angle);
                steer = getSteer(error, P_DRIVE_COEFF);

                // if driving in reverse, the motor correction also needs to be reversed
                if (distance < 0)
                    steer *= -1.0;

                leftSpeed = speed - steer;
                rightSpeed = speed + steer;

                // Normalize speeds if either one exceeds +/- 1.0;
                max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
                if (max > 1.0)
                {
                    leftSpeed /= max;
                    rightSpeed /= max;
                }

                robot.backLeftMotor.setPower(leftSpeed);
                robot.backRightMotor.setPower(rightSpeed);


            }

            // Stop all motion;
            robot.backLeftMotor.setPower(0);
            robot.backRightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

/*
    */
/**
     *  Method to spin on central axis to point in a new direction.
     *  Move will stop if either of these conditions occur:
     *  1) Move gets to the heading (angle)
     *  2) Driver stops the opmode running.
     *
     * @param speed Desired speed of turn.
     * @param angle      Absolute Angle (in Degrees) relative to last gyro reset.
     *                   0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *                   If a relative angle is required, add/subtract from current heading.
     *//*

    public void gyroTurn (  double speed, double angle) {

        // keep looping while we are still active, and not on heading.
        while (opModeIsActive() && !onHeading(speed, angle, P_TURN_COEFF)) {
            // Update telemetry & Allow time for other processes to run.
        }
    }

    */
/**
     *  Method to obtain & hold a heading for a finite amount of time
     *  Move will stop once the requested time has elapsed
     *
     * @param speed      Desired speed of turn.
     * @param angle      Absolute Angle (in Degrees) relative to last gyro reset.
     *                   0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *                   If a relative angle is required, add/subtract from current heading.
     * @param holdTime   Length of time (in seconds) to hold the specified heading.
     *//*

    public void gyroHold( double speed, double angle, double holdTime) {

        ElapsedTime holdTimer = new ElapsedTime();

        // keep looping while we have time remaining.
        holdTimer.reset();
        while (opModeIsActive() && (holdTimer.time() < holdTime)) {
            // Update telemetry & Allow time for other processes to run.
            onHeading(speed, angle, P_TURN_COEFF);
            telemetry.update();
        }

        // Stop all motion;
        robot.backLeftMotor.setPower(0);
        robot.backRightMotor.setPower(0);
    }

    */
/**
     * Perform one cycle of closed loop heading control.
     *
     * @param speed     Desired speed of turn.
     * @param angle     Absolute Angle (in Degrees) relative to last gyro reset.
     *                  0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *                  If a relative angle is required, add/subtract from current heading.
     * @param PCoeff    Proportional Gain coefficient
     * @return
     *//*

    boolean onHeading(double speed, double angle, double PCoeff) {
        double   error ;
        double   steer ;
        boolean  onTarget = false ;
        double leftSpeed;
        double rightSpeed;

        // determine turn power based on +/- error
        error = getError(angle);

        if (Math.abs(error) <= HEADING_THRESHOLD) {
            steer = 0.0;
            leftSpeed  = 0.0;
            rightSpeed = 0.0;
            onTarget = true;
        }
        else {
            steer = getSteer(error, PCoeff);
            rightSpeed  = speed * steer;
            leftSpeed   = -rightSpeed;
        }

        // Send desired speeds to motors.
        robot.backLeftMotor.setPower(leftSpeed);
        robot.backRightMotor.setPower(rightSpeed);


        return onTarget;
    }

    */
/**
     * getError determines the error between the target angle and the robot's current heading
     * @param   targetAngle  Desired angle (relative to global reference established at last Gyro Reset).
     * @return  error angle: Degrees in the range +/- 180. Centered on the robot's frame of reference
     *          +ve error means the robot should turn LEFT (CCW) to reduce error.
     *//*

    public double getError(double targetAngle) {

        double robotError;

        // calculate error in -179 to +180 range  (
        robotError = targetAngle -  .getIntegratedZValue();
        while (robotError > 180)  robotError -= 360;
        while (robotError <= -180) robotError += 360;
        return robotError;
    }


    public double getSteer(double error, double PCoeff) {
        return Range.clip(error * PCoeff, -1, 1);
    }
    */

}
