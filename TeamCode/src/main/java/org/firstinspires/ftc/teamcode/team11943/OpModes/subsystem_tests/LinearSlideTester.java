package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;

@TeleOp(group="Isabelle")
public class LinearSlideTester extends OpMode {

    private LinearSlide vSlide;
    private AngleMechanism angleMechanism;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        vSlide = new LinearSlide(hardwareMap, telemetry);
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);

        angleMechanism.raised_position();
        vSlide.stop();
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

        // TESTING TO SEE IF THIS NEEDS TO BE CONSTANTLY CALLED
        // angleMechanism.raised_position();

        if (gamepad1.right_trigger > 0) {
            vSlide.raise(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > 0) {
            vSlide.lower(gamepad1.left_trigger);
        }
        else if (gamepad1.y) {
            vSlide.undoLimit();
        }
        else if (gamepad1.x) {
            vSlide.redoLimit();
            telemetry.addLine("redo pressed");
        }
        else {
            vSlide.stop();
        }
        vSlide.reportTelemetry();
    }

    @Override
    public void stop() {
        vSlide.stop();
        vSlide.disable_brake();
        telemetry.addLine("STOP() CALLED");
    }
}
