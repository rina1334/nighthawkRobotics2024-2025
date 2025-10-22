package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.ArmMotion;

@TeleOp(group="Isabelle")
public class ClawTester extends OpMode {


    private Claw claw;
//    private ArmMotion am;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        claw = new Claw(hardwareMap, telemetry);
//        am = new ArmMotion(hardwareMap, telemetry);
    }

    public void loop() {

        telemetry.addLine("code uploaded!");

//        boolean xb = gamepad1.cross;
//        boolean cb = gamepad1.circle;
//        boolean sb = gamepad1.square;

      //  c.grab(xb);
//        am.slide(cb,sb);


        if(gamepad1.square) {
            claw.setOpenPos();
        }
        else if (gamepad1.circle) {
            claw.setMidOpenPos();
        }
        else if(gamepad1.cross) {
            claw.setClosedPos();
        }

    }
}