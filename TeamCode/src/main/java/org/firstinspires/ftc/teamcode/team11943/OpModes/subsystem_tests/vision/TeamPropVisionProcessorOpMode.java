package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.team11943.subsystems.vision.TeamPropVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous
public class TeamPropVisionProcessorOpMode extends OpMode {
    TeamPropVisionProcessor teamPropProcessor;
    VisionPortal visionPortal;

    @Override
    public void init() {
        teamPropProcessor = new TeamPropVisionProcessor();
        visionPortal  = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"),
                teamPropProcessor);
    }

    @Override
    public void stop() {
        visionPortal.stopStreaming();
    }

    @Override
    public void loop() {
        TeamPropVisionProcessor.Selected x = teamPropProcessor.getSelection();
        telemetry.addData("selected", x);
        switch(x) {
            case LEFT:
                gamepad1.setLedColor(1, 0, 0, 100);
                break;
            case MIDDLE:
                gamepad1.setLedColor(0, 1, 0, 100);
                break;
            case RIGHT:
                gamepad1.setLedColor(1, 0, 1, 100);
                break;
            case NONE:
                gamepad1.setLedColor(0, 0, 0, 100);
                break;
        }
    }
}
