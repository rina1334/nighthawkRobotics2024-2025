package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;

@Disabled
@TeleOp(group = "Isabelle")
public class AngleMechanismTest extends OpMode{
    private AngleMechanism angleMechanism;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);

        //report PID coefficients
        angleMechanism.reportPIDCoefficients();
        angleMechanism.changePIDCoefficients();
        angleMechanism.reportPIDCoefficients();
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
    public void loop() {
//        if (gamepad1.right_trigger > 0) {
//            // angleMechanism.raised_position();
//            angleMechanism.raise_no_pid(gamepad1.right_trigger);
//        }
//        else if (gamepad1.left_trigger > 0) {
//            // angleMechanism.lowered_position();
//            angleMechanism.lower_no_pid(gamepad1.left_trigger);
//        }
////        else {
////            angleMechanism.stop();
////        }
//
//
//        else if (gamepad1.right_bumper) {
//            // angleMechanism.raised_position();
//            angleMechanism.raise();
//        }
//        else if (gamepad1.left_bumper) {
//            // angleMechanism.lowered_position();
//            angleMechanism.lower();
//        }
//        else if (gamepad1.b) {
//            servo.end_position();
//        }
//        else if (gamepad1.right_trigger > 0) {
//            servo.set_position(gamepad1.right_trigger);
//        }

        if (gamepad2.triangle) {
            angleMechanism.raised_position();
        }
        else if (gamepad2.cross) {
            angleMechanism.lowered_position();
        }
        else if (gamepad2.circle) {
            angleMechanism.low_mid_position();
        }
        else if (gamepad2.square) {
            angleMechanism.high_mid_position();
        }
        else if (gamepad2.left_bumper) {
            angleMechanism.endgame_position();
        }

        angleMechanism.reportTelemetry();
    }

    @Override
    public void stop() {
        telemetry.addLine("STOP() CALLED");
    }
}

