package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.RobotCentricMecanum;

@Disabled
@TeleOp(group="Isabelle")
public class RobotCentricMecanumTester extends OpMode {

    private RobotCentricMecanum drive;

    @Override
    public void init() {
//        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        drive = new RobotCentricMecanum(hardwareMap, telemetry);
        drive.stop();
    }

    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        drive.drive(x, y, rx);

        // A button is an emergency stop
        if (gamepad1.a) {
            drive.stop();
        }
    }
}
