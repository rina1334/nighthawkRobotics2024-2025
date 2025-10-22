package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.SwyftSlide;

@TeleOp(group="Isabelle")
public class SwyftSlideTester extends OpMode {

    private SwyftSlide swyftSlide;
    private AngleMechanism angleMechanism;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        swyftSlide = new SwyftSlide(hardwareMap, telemetry);
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);
        angleMechanism.raised_position();
        swyftSlide.stop();
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
        angleMechanism.raised_position();

        if (gamepad1.a) {
            telemetry.addLine("GAMEPAD A: STOPPING");
            swyftSlide.stop();
            return;
        }

        if (gamepad1.x) {
//            telemetry.addLine("GAMEPAD X");
//            if (gamepad1.dpad_up) {
//                telemetry.addLine("DPAD_UP");
//                swyftSlide.moveToTopPosition();
//            }
//            else if (gamepad1.dpad_right) {
//                telemetry.addLine("DPAD_RIGHT");
//                swyftSlide.moveToMiddlePosition();
//            }
//            else if (gamepad1.dpad_down) {
//                telemetry.addLine("DPAD_DOWN");
//                swyftSlide.moveToBottomPosition();
//            }
        }
        else if (gamepad1.y) {
            telemetry.addLine("GAMEPAD Y");
            if (gamepad1.dpad_up) {
                telemetry.addLine("DPAD_UP");
                swyftSlide.raiseALittle();
            }
            else if (gamepad1.dpad_down) {
                telemetry.addLine("DPAD_DOWN");
                swyftSlide.lowerALittle();
            }
        }
        swyftSlide.reportTelemetry();
    }

    @Override
    public void stop() {
        swyftSlide.stop();
        telemetry.addLine("STOP() CALLED");
    }
}
