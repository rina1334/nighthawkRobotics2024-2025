package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;
import org.firstinspires.ftc.teamcode.team11943.subsystems.SillyServo;

@Disabled
@TeleOp(group="Isabelle")
public class ArmTest extends OpMode {

    private SillyServo servo;
    private LinearSlide slide;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");

        //servo
        servo = new SillyServo(hardwareMap, telemetry);

        //linear slide
        slide = new LinearSlide(hardwareMap, telemetry);
        slide.stop();
    }

    @Override
    public void init_loop() {
        telemetry.addLine("INIT_LOOP() CALLED");
        telemetry.addLine("helo world");

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
//        else if (gamepad1.right_trigger > 0) {
//            servo.set_position(gamepad1.right_trigger);
//        }

        servo.reportTelemetry();

        if (gamepad1.right_trigger > 0) {
            slide.raise(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > 0) {
            slide.lower(gamepad1.left_trigger);
        }
        else {
            slide.stop();
        }
        slide.reportTelemetry();
    }

    @Override
    public void stop() {
        telemetry.addLine("STOP() CALLED");
        slide.stop();
        slide.disable_brake();
    }
}
