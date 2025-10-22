package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;

//@Disabled
@TeleOp(group="Isabelle")
public class LinearSlide2Tester extends OpMode {

    private LinearSlide2 slide;
    private AngleMechanism angleMechanism;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        slide = new LinearSlide2(hardwareMap, telemetry);
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);
        angleMechanism.raised_position();
        slide.stop();
    }

//    @Override
//    public void init_loop() {
//        telemetry.addLine("INIT_LOOP() CALLED");
//    }

    @Override
    public void start() {
        telemetry.addLine("START() CALLED");
    }

    @Override
    public void loop(){
        if (gamepad1.a) {
            telemetry.addLine("GAMEPAD A: STOPPING");
            slide.stop();
            return;
        }

        if (gamepad1.x) {
            telemetry.addLine("GAMEPAD X");
            if (gamepad1.dpad_up) {
                telemetry.addLine("DPAD_UP");
                slide.setPosition(3000); // 4500);
            }
            else if (gamepad1.dpad_left) {
                telemetry.addLine("DPAD_LEFT");
                slide.setPosition(2000); //2000);
            }
            else if (gamepad1.dpad_right) {
                telemetry.addLine("DPAD_RIGHT");
                slide.setPosition(1000); //2000);
            }
            else if (gamepad1.dpad_down) {
                telemetry.addLine("DPAD_DOWN");
                slide.setPosition(0); // 200);
            }
        }
        else if (gamepad1.y) {
            telemetry.addLine("GAMEPAD Y");
            if (gamepad1.dpad_up) {
                telemetry.addLine("DPAD_UP");
                slide.raisePosition(50);
            }
            else if (gamepad1.dpad_down) {
                telemetry.addLine("DPAD_DOWN");
                slide.lowerPosition(50);
            }
        }
//        if (gamepad1.right_trigger > 0) {
//            slide.raise(gamepad1.right_trigger);
//        }
//        else if (gamepad1.left_trigger > 0) {
//            slide.lower(gamepad1.left_trigger);
//        }
//        else if (gamepad1.y) {
//            slide.undoLimit();
//        }
//        else if (gamepad1.x) {
//            slide.redoLimit();
//            telemetry.addLine("redo pressed");
//        }
//        else {
//            slide.stop();
//        }
        slide.reportTelemetry();
        angleMechanism.reportTelemetry();
    }

    @Override
    public void stop() {
        slide.stop();
        slide.disable_brake();
        telemetry.addLine("STOP() CALLED");
    }
}
