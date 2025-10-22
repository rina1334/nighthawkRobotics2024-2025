package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.FieldCentricMecanum;

@Disabled
@TeleOp(group="Isabelle")
public class FieldCentricMecanumTester extends OpMode {

    private FieldCentricMecanum fcm;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        fcm = new FieldCentricMecanum(hardwareMap, telemetry);


        fcm.stop();

    }

    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;


        if (gamepad1.options) {
            fcm.resetYaw();
            telemetry.addLine("resetting yaw !!!!");
        }


        fcm.drive(x, y, rx);
    }
}
