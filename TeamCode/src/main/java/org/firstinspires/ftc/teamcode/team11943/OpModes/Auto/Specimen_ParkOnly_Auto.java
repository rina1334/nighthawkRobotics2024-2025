package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;

// in case an alliance member has an even better basket auto than ours
// and insists on using it, here we start in the specimen position and
// just auto-park in observation zone
//
// based on zero start and OTOS localization relative to zero start

@Autonomous(group="Isabelle", preselectTeleOp = "FinalSuperQualIntoTheDeep")
public class Specimen_ParkOnly_Auto extends OpMode {
    AngleMechanism angleMechanism;
    SparkFunOTOSDrive drive;

    Vector2d startPos = new Vector2d(0,0);
    double startHeading = Math.toRadians(0);
    Pose2d startPose = new Pose2d(startPos, startHeading);

    Vector2d firstPos = new Vector2d(6, 0);   // move forward x inches before strafing
    Vector2d parkingPos = new Vector2d(6, -32); // move right towards parking area

    public Action moveFromStartToObservationParking() {
        return drive.actionBuilder(drive.pose)
                .splineToConstantHeading(firstPos, startHeading)
                .strafeToConstantHeading(parkingPos)
                .build();
    }

    public Pose2d getCurrentPose() {
        telemetry.addData("getCurrentPose", drive.pose);
        return drive.pose;
    }

    public Action raiseAngleMechanism() {
        return angleMechanism.raisedAction();
    }

    @Override
    public void init() {
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);
        drive = new SparkFunOTOSDrive(hardwareMap, startPose);

        Actions.runBlocking(
                raiseAngleMechanism()
        );
    }

    @Override
    public void start() {
        Actions.runBlocking(
                moveFromStartToObservationParking()
        );
    }

    @Override
    public void loop() {
        // do nothing
    }
}
