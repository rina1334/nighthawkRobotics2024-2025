package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;

@Autonomous(group="Isabelle", preselectTeleOp = "Qual1IntoTheDeep")
@Config
public class Auto_OTOS_Chamber_Zero_Start extends OpMode {
    SparkFunOTOSDrive drive;
    LinearSlide slide;
    AngleMechanism angleServo;
    Claw claw;

    //public static int SLIDE_HIGH_POS = 2000;
    //public static double y_final = 24 + 8; //7.5 is the distance from center of robot to bar

    Vector2d startPos = new Vector2d(0, 0);
    double startHeading = Math.toRadians(0);
    Pose2d startPose = new Pose2d(startPos, startHeading);

    @Override
    public void init() {
        drive = new SparkFunOTOSDrive(hardwareMap, startPose);
        slide = new LinearSlide(hardwareMap, telemetry);
        angleServo = new AngleMechanism(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);
    }

    @Override
    public void start() {

        // set the angle mechanism straight up
        angleServo.raised_position();

        Vector2d highChamber = new Vector2d(30.07 + 1.5, 14.19);
        Vector2d observationZone = new Vector2d(2.2, -29.25);
        double endHeading = Math.toRadians(startHeading + 180);

        Action auto_action = drive.actionBuilder(startPose)
                .stopAndAdd(slide.raiseAction(1780))    // raise slide so it clear high chamber for specimen)
                .setTangent(startHeading)
                .splineToConstantHeading(highChamber, startHeading)
                .stopAndAdd(slide.lowerAction(800))     // lower slide just enough to clip the specimen onto the high chamber
                .waitSeconds(1.0)
                .stopAndAdd(claw.openClawAction())                  // release the specimen from the claw
                .setTangent(endHeading)
                .splineToConstantHeading(observationZone, endHeading)
                .stopAndAdd(new ParallelAction(
                        slide.lowerAction(),
                        claw.closeClawAction()))
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
