package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.SillyServo;

@Disabled
@TeleOp(group="Isabelle")
public class SillyServoTest extends OpMode {

    private SillyServo servo;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        servo = new SillyServo(hardwareMap, telemetry);
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
    public void loop() {
        if (gamepad1.right_bumper) {
            servo.max_position();
        }
        else if (gamepad1.left_bumper) {
            servo.min_position();
        }
        else if (gamepad1.right_trigger > 0) {
            servo.set_position(gamepad1.right_trigger);
        }

        servo.reportTelemetry();
    }

    @Override
    public void stop() {
        telemetry.addLine("STOP() CALLED");
    }
}
