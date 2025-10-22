package org.firstinspires.ftc.teamcode.team11943.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;

@TeleOp(group="util")
public class ResetLinearSlide extends OpMode {
    LinearSlide2 slide;

    @Override
    public void init() {
        slide = new LinearSlide2(hardwareMap, telemetry);
        telemetry.addLine("Pressing start will call slide.disable_brake()");
    }

    @Override
    public void start() {
        slide.disable_brake();
        telemetry.addLine("slide brake should be disabled");
    }

    @Override
    public void loop() {
    }
}
