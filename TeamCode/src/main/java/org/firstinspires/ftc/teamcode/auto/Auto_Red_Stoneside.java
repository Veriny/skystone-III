package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.FoundationClaw;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SkystoneContour;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Red(Stoneside)_Collect_Deposit_FoundationDrag_Park", group = "test")
public class Auto_Red_Stoneside extends LinearOpMode {
    public Drivetrain robot;
    public Intake intake;
    public Lift lift;
    public FoundationClaw foundationClaw;
    public SkystoneContour vision;
    public OpenCvCamera phoneCam;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Drivetrain(hardwareMap.dcMotor.get("topLeftMotor"), hardwareMap.dcMotor.get("bottomLeftMotor"), hardwareMap.dcMotor.get("topRightMotor"), hardwareMap.dcMotor.get("bottomRightMotor"), true, telemetry);
        intake = new Intake(hardwareMap.dcMotor.get("leftIntake"), hardwareMap.dcMotor.get("rightIntake"));
        lift = new Lift(hardwareMap.dcMotor.get("liftMotor"), hardwareMap.dcMotor.get("v4bMotor"), hardwareMap.servo.get("clawServo"));
        foundationClaw = new FoundationClaw(hardwareMap.servo.get("leftFoundationServo"), hardwareMap.servo.get("rightFoundationServo"));
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        waitForStart();
        phoneCam.openCameraDevice();
        phoneCam.setPipeline(vision);
        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
        lift.release();
        robot.strafe(24, 0.5);
        lift.liftV4BMotor();
        boolean hasSkystone = false;
//        for(int i = 0; i < 3; i++) {
//            if (!robot.skystoneIsCentered()) {
//                robot.drive(8, 0.5);
//            }
//            else {
//                robot.turn(90, 0.5);
//                intake.succ(0.69420 * 1.1);
//                robot.drive(18, 0.3);
//                intake.noSucc();
//                lift.restV4BMotor();
//                lift.hold();
//                robot.drive(-16, 0.6);
//                hasSkystone = true;
//                return;
//            }
//        }

        if(!hasSkystone) {
            robot.drive(-4, 0.5);
            robot.turn(45, 0.5);
            intake.succ(0.69420 * 1.1);
            robot.drive(26, 0.2);
            intake.noSucc();
            robot.residentSleeper(100);
            lift.restV4BMotor();
            robot.residentSleeper(1000);
            lift.hold();
            robot.drive(-38, 0.6);
        }

        phoneCam.stopStreaming();
        robot.turn(-45, 0.4);
        robot.drive(-68, 0.6);
        robot.turn(-90, 0.5);

        lift.dumpLiftMotor();
        robot.residentSleeper(2500);
        lift.dumpV4BMotor();
        robot.residentSleeper(1000);
        robot.drive(-8, 0.3);
        lift.release();
        foundationClaw.push();
        robot.residentSleeper(500);
        lift.restV4BMotor();
        robot.residentSleeper(1000);
        lift.restLiftMotor();
        robot.residentSleeper(750);
        robot.arcTurn(90, 24, 0.2, true);
        foundationClaw.rest();
        robot.drive(-1, 0.5);
        robot.strafe(24, 0.6);
        robot.drive(-20, 0.6);
        robot.strafe(20, 0.5);



    }
}
