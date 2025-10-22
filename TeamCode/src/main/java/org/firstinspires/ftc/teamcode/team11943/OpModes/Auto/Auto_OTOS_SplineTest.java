package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;

@Disabled
@Autonomous(group="Isabelle")
public class Auto_OTOS_SplineTest extends OpMode {
    private SparkFunOTOSDrive drive;
    private Pose2d beginPose;

    @Override
    public void init(){
        beginPose = new Pose2d(0, 0, 0);
        drive = new SparkFunOTOSDrive(hardwareMap, beginPose);
    }
    @Override
    public void start(){
        Actions.runBlocking(
            drive.actionBuilder(beginPose)
                .turn(2*Math.PI)
                .splineToConstantHeading(new Vector2d(30, 30), Math.PI / 2)
                .splineToConstantHeading(new Vector2d(0, 0), 0)
                .build()
        );
    }

    @Override
    public void loop(){
    }
}

