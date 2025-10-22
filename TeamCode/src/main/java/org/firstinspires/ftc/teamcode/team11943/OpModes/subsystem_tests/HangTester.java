package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.Hang;

//@Disabled
@TeleOp(group="Isabelle")
public class HangTester extends OpMode {

    private Hang hang;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        hang = new Hang(hardwareMap, telemetry);
        hang.stop();
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
        if (gamepad1.right_trigger > 0) {
            hang.raise(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > 0) {
            hang.lower(gamepad1.left_trigger);
        }
        else {
            hang.stop();
        }

        hang.reportTelemetry();
    }

    @Override
    public void stop() {
        hang.stop();
        hang.disable_brake();
        telemetry.addLine("STOP() CALLED");
    }
}
