package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.HorizontalExtension;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;

@TeleOp(group="Isabelle")
public class LinearSlide3Tester extends OpMode {
    private LinearSlide2 vSlidePID;
   // private LinearSlide vSlideNormal;
    private HorizontalExtension hSlide;
    private AngleMechanism angleMechanism;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");
        vSlidePID = new LinearSlide2(hardwareMap, telemetry);
      //  vSlideNormal = new LinearSlide(hardwareMap, telemetry);
        hSlide = new HorizontalExtension(hardwareMap, telemetry);
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);
    }

    @Override
    public void loop(){
//        if (gamepad1.a) {
//            telemetry.addLine("GAMEPAD A: STOPPING");
//            vSlide.stop();
//            return;
//        }
//
//        if (gamepad1.x) {
//            telemetry.addLine("GAMEPAD X");
//        }
//        if (gamepad1.dpad_up) {
//            telemetry.addLine("DPAD_UP");
//            vSlide.setPosition(4500);
//        }
//        else if (gamepad1.dpad_right) {
//            telemetry.addLine("DPAD_RIGHT");
//            vSlide.setPosition(2000);
//        }
//        else if (gamepad1.dpad_down) {
//            telemetry.addLine("DPAD_DOWN");
//            vSlide.setPosition(200);
//        }
//        else if (gamepad1.y) {
//            telemetry.addLine("GAMEPAD Y");
//                if (gamepad1.dpad_up) {
//                    telemetry.addLine("DPAD_UP");
//                    vSlide.raisePosition(50);
//                }
//                else if (gamepad1.dpad_down) {
//                    telemetry.addLine("DPAD_DOWN");
//                    vSlide.lowerPosition(50);
//                }
//        }
//            vSlide.reportTelemetry();

           // angleMechanism.raised_position();

            angleMechanism.raised_position();

            if (gamepad1.right_trigger > 0) {
                vSlidePID.raisePosition(50);
                hSlide.extend();
            }
            else if (gamepad1.left_trigger > 0) {
                vSlidePID.lowerPosition(50);
                hSlide.retract();
            }

            vSlidePID.reportTelemetry();

    }

    @Override
    public void stop() {
        vSlidePID.stop();
        vSlidePID.disable_brake();
        telemetry.addLine("STOP() CALLED");
    }
}