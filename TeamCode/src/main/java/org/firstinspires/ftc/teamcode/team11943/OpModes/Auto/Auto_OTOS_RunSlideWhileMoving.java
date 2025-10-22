package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;

//@Autonomous(group="Isabelle")
@Disabled
public class Auto_OTOS_RunSlideWhileMoving extends OpMode {
    SparkFunOTOSDrive drive;
    LinearSlide slide;

    @Override
    public void init() {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        drive = new SparkFunOTOSDrive(hardwareMap, beginPose);
        slide = new LinearSlide(hardwareMap, telemetry);
    }

    @Override
    public void start() {
        TrajectoryActionBuilder builder = drive.actionBuilder(drive.pose);
        TrajectoryActionBuilder b1 = builder
            .turn(2*Math.PI)
            .splineTo(new Vector2d(0, 30), Math.PI/2);

        // To allow for SequentialActions() that don't keep resetting to
        // the previous action's begin position, build use the previous
        // TrajectoryActionBuilder and call .endTrajectory().fresh() on it
        //
        TrajectoryActionBuilder b2 = b1.endTrajectory().fresh()
            .strafeTo(new Vector2d(30, 30));

        Action trajectoryAction1 = b1.build();
        Action trajectoryAction2 = b2.build();

        Actions.runBlocking(
            new SequentialAction(
                new ParallelAction(
                    trajectoryAction1,
                    slide.raiseAction()
                ),
                new SleepAction(2.0),
                new ParallelAction(
                    trajectoryAction2,
                    slide.lowerAction()
                )
            )
        );
    }

    @Override
    public void loop() {
        slide.reportTelemetry();
    }

}
