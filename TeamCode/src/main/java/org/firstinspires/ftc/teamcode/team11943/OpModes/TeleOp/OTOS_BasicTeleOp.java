package org.firstinspires.ftc.teamcode.team11943.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;

@TeleOp
public class OTOS_BasicTeleOp extends OpMode {
    SparkFunOTOSDrive drive;
    LinearSlide slide;
    Telemetry telemetry;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, new Pose2d(0, 0, 0));
    }

    @Override
    public void loop() {
        // Controlling the linear slide
        if (gamepad1.left_trigger >= 0) {
            slide.raise(gamepad1.left_trigger);
        }
        else if (gamepad1.right_trigger > 0) {
            slide.lower(gamepad1.right_trigger);
        } else {
            slide.stop();
        }

        // Driving around with gamepad1
        drive.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x
                ),
                -gamepad1.right_stick_x
        ));

        drive.updatePoseEstimate();

        telemetry.addData("x", drive.pose.position.x);
        telemetry.addData("y", drive.pose.position.y);
        telemetry.addData("heading (deg)", Math.toDegrees(drive.pose.heading.toDouble()));
    }

    @Override
    public void stop() {
        slide.stop();
    }
}

