package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;

@Disabled
@Autonomous(group="Isabelle", preselectTeleOp = "Qual1IntoTheDeep")
@Config
public class Auto_OTOS_Chamber_MeepMeep extends OpMode {
    SparkFunOTOSDrive drive;
    LinearSlide slide;
    AngleMechanism angleServo;
    Claw claw;

    //public static int SLIDE_HIGH_POS = 2000;
    //public static double y_final = 24 + 8; //7.5 is the distance from center of robot to bar

    boolean RED_ALLIANCE = true;
    double ALLIANCE_MULT = RED_ALLIANCE ? 1 : -1;

    double robotFrontDistance = 10.0;    // distance from front of robot to center of robot
    double robotBackDistance = 7.0;     // distance from back of robot to center of robot

    Vector2d startPos = new Vector2d(12.0, -70.0 + robotBackDistance).times(ALLIANCE_MULT);
    double startHeading = Math.toRadians(90.0) * ALLIANCE_MULT;
    Pose2d startPose = new Pose2d(startPos, startHeading);

    @Override
    public void init() {
        telemetry.addData("RED_ALLIANCE", RED_ALLIANCE);
        drive = new SparkFunOTOSDrive(hardwareMap, startPose);
        slide = new LinearSlide(hardwareMap, telemetry);
        angleServo = new AngleMechanism(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);
    }

    @Override
    public void start() {

        // set the angle mechanism straight up
        angleServo.raised_position();

        Vector2d highChamber = new Vector2d(0.0, -24.0 - robotFrontDistance).times(ALLIANCE_MULT);
        Vector2d endPos = new Vector2d(0.0, -48.0 + robotFrontDistance).times(ALLIANCE_MULT);

        Action auto_action = drive.actionBuilder(startPose)
                .stopAndAdd(slide.raiseAction(1780))    // raise slide so it clear high chamber for specimen)
                //.setReversed(true)
                .setTangent(startHeading)
                .splineToConstantHeading(highChamber, startHeading)
                .stopAndAdd(slide.lowerAction(800))     // lower slide just enough to clip the specimen onto the high chamber
                .waitSeconds(1.0)
                .stopAndAdd(claw.openClawAction())                  // release the specimen from the claw
                .waitSeconds(1.0)
                .setTangent(-startHeading)
                .splineToConstantHeading(startPos, -startHeading)
                .stopAndAdd(new ParallelAction(
                        slide.lowerAction(),
                        claw.closeClawAction()
                ))
                .build();

        Actions.runBlocking(
                auto_action
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
