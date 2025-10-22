package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.HorizontalExtension;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;
import org.firstinspires.ftc.teamcode.team11943.subsystems.RobotCentricMecanum;

public class TeleopAutomations {
    AngleMechanism angleMechanism;
    Claw claw;
    HorizontalExtension hSlide;
    RobotCentricMecanum rcmDrive;
    LinearSlide2 vSlide;
    Telemetry telemetry;

    public TeleopAutomations(HardwareMap hardwareMap, Telemetry tele) {
        angleMechanism = new AngleMechanism(hardwareMap, tele);
        claw = new Claw(hardwareMap, tele);
        rcmDrive = new RobotCentricMecanum(hardwareMap, tele);
        vSlide = new LinearSlide2(hardwareMap, tele);
        hSlide = new HorizontalExtension(hardwareMap, tele);
        telemetry = tele;
    }

    public Action retractFromSubmersible() {
        telemetry.addLine("retract from submersible");
        return new SequentialAction(
                angleMechanism.lowPartiallyRaisedAction(),
                new SleepAction(0.5),
                hSlide.retractAction(),
                new SleepAction(0.5),
                rcmDrive.backupAction(),
                angleMechanism.raisedAction()
        );
    }
}
