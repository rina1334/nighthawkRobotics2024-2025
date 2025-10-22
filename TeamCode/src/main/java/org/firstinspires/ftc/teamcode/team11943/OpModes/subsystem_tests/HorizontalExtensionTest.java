package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.HorizontalExtension;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;

@TeleOp(group="Isabelle")
public class HorizontalExtensionTest extends OpMode {

    private HorizontalExtension hSlide;
    private AngleMechanism angleMechanism;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        hSlide = new HorizontalExtension(hardwareMap, telemetry);
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);
    }

    @Override
    public void init_loop() {
        telemetry.addLine("INIT_LOOP() CALLED");
    }

    @Override
    public void start() {
        telemetry.addLine("START() CALLED");
    }

    @Override
    public void loop(){
        angleMechanism.lowered_position();
        if (gamepad1.right_trigger > 0) {
            hSlide.extend();
        }
        else if (gamepad1.left_trigger > 0) {
            hSlide.retract();
        }
        else if (gamepad1.dpad_down) {
            hSlide.reset();
        }

    }

}
