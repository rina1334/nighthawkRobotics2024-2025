package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;

@Disabled
@Autonomous(group="Isabelle", preselectTeleOp = "Qual1IntoTheDeep")
@Config
public class Auto_OTOS_Basket_MeepMeep extends OpMode {
    SparkFunOTOSDrive drive;
    LinearSlide2 slide;
    AngleMechanism angleServo;
    Claw claw;

    public static int SLIDE_HIGH_POS = 2000;
    public static double y_final = 24 + 8; //7.5 is the distance from center of robot to bar

    boolean RED_ALLIANCE = true;
    double ALLIANCE_MULT = RED_ALLIANCE ? 1 : -1;

    double robotFrontDistance = 8.0;
    double robotBackDistance = 7.0;

    Vector2d startPos = new Vector2d(-12.0,-70.0+robotBackDistance).times(ALLIANCE_MULT);
    double startHeading = Math.toRadians(90.0)*ALLIANCE_MULT;
    Pose2d startPose = new Pose2d(startPos, startHeading);

    @Override
    public void init() {
        telemetry.addData("RED_ALLIANCE", RED_ALLIANCE);
        drive = new SparkFunOTOSDrive(hardwareMap, startPose);
        slide = new LinearSlide2(hardwareMap, telemetry);
        angleServo = new AngleMechanism(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);
    }

    @Override
    public void start() {

        // set the angle mechanism straight up
        angleServo.raised_position();

        Vector2d waypoint = new Vector2d(-36,-36);

        //Vector2d basketPos = new Vector2d(-56,-56).times(ALLIANCE_MULT);
        //Vector2d basketPos = new Vector2d(-53,-53).times(ALLIANCE_MULT);
        //Vector2d basketPos = new Vector2d(-52,-55).times(ALLIANCE_MULT);
        //Vector2d basketPos = new Vector2d(-51,-55).times(ALLIANCE_MULT);
        //Vector2d basketPos = new Vector2d(-49.2,-54.6).times(ALLIANCE_MULT);
        //Vector2d basketPos = new Vector2d(-49.8,-55.0).times(ALLIANCE_MULT);
        Vector2d basketPos = new Vector2d(-52.5,-54.0).times(ALLIANCE_MULT);
        //Vector2d basketPos = new Vector2d(-52.7,-53.3).times(ALLIANCE_MULT);
        //double basketHeading = Math.toRadians(-135);
        double basketHeading = Math.toRadians(-145);

        Action basketAction = drive.actionBuilder(startPose)
                .setTangent(startHeading)
                .splineToLinearHeading(new Pose2d(basketPos, basketHeading), basketHeading)
                .waitSeconds(1.0)
                .stopAndAdd(slide.setPositionAction(4400))     // raise slide above basket
                //.stopAndAdd(slide.setPositionAction(2000))     // raise slide above basket
                //.waitSeconds(1.0)
                // MADE PRIVATE
                //.stopAndAdd(angleServo.changeAngleAction(0.25))     // tilt partly towards basket
                .waitSeconds(1.0)
                .stopAndAdd(claw.openClawAction())
                .waitSeconds(1.0)
                .stopAndAdd(claw.closeClawAction())
                .waitSeconds(1.0)
                // MADE PRIVATE
                //.stopAndAdd(angleServo.changeAngleAction(0.48))
                .waitSeconds(1.0)
                .stopAndAdd(slide.setPositionAction(2000, 0.5))
                .waitSeconds(1.0)
                .stopAndAdd(slide.setPositionAction(200, 0.5))
                .build();

        Actions.runBlocking(
                basketAction
        );
    }

    @Override
    public void loop() {
        slide.reportTelemetry();
        claw.reportTelemetry();
        drive.updatePoseEstimate();
        telemetry.addData("x", drive.pose.position.x);
        telemetry.addData("y", drive.pose.position.y);
        telemetry.addData("heading (deg)", Math.toDegrees(drive.pose.heading.toDouble()));
    }

}
