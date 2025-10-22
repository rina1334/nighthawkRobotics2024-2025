package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.subsystems.RobotCentricMecanum;
import org.firstinspires.ftc.teamcode.team11943.subsystems.TrainingDriveTrain;

@TeleOp(group="Isabelle")
public class TrainingDriveTrainTester extends OpMode {

    private TrainingDriveTrain drive;

    @Override
    public void init() {
        drive = new TrainingDriveTrain(hardwareMap, telemetry);
        drive.stop();
        telemetry.addLine("INIT() CALLED");
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
