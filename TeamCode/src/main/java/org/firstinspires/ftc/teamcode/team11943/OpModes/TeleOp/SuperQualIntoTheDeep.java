package org.firstinspires.ftc.teamcode.team11943.OpModes.TeleOp;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team11943.OpModes.Auto.TeleopAutomations;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism_CustomPID;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Hang;
import org.firstinspires.ftc.teamcode.team11943.subsystems.HorizontalExtension;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;
import org.firstinspires.ftc.teamcode.team11943.subsystems.RobotCentricMecanum;

@Disabled
@TeleOp(group="A1")
public class SuperQualIntoTheDeep extends OpMode {

    private AngleMechanism_CustomPID angleMechanism;
    //private LinearSlide vSlideNormal;
    private LinearSlide2 vSlidePID;
    private RobotCentricMecanum drive;
    private Hang hang;
    private Claw claw;
    private HorizontalExtension hSlide;
    private TeleopAutomations automations;

    @Override
    public void init(){
        telemetry.addLine("INIT() CALLED");

        //angle mechanism
        angleMechanism = new AngleMechanism_CustomPID(hardwareMap, telemetry);

        //vertical linear slide
        //vSlideNormal = new LinearSlide(hardwareMap, telemetry);
        //vSlideNormal.stop();

        vSlidePID = new LinearSlide2(hardwareMap, telemetry);

        //drivetrain: rcm
//        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        drive = new RobotCentricMecanum(hardwareMap, telemetry);
        drive.stop();
//
        //hang
        hang = new Hang(hardwareMap, telemetry);
        hang.stop();

        //claw
        claw = new Claw(hardwareMap, telemetry);

        //horizontal extension
        hSlide = new HorizontalExtension(hardwareMap, telemetry);

        // automations
        automations = new TeleopAutomations(hardwareMap, telemetry);

//        //report PID coefficients
//        angleMechanism.reportPIDCoefficients();
//        angleMechanism.changePIDCoefficients();
//        angleMechanism.reportPIDCoefficients();

        hSlide.endgameSetPosition();
        angleMechanism.raised_position();
    }

    @Override
    public void start() {
        telemetry.addLine("START() CALLED");
    }

    @Override
    public void loop() {

        //angle control
        if (gamepad2.triangle && hSlide.checkIfSafeToRaise()) {
            // angleMechanism.max_position();
           // hSlide.endgameSetPosition();
            angleMechanism.raised_position();
            //angleMechanism.raise();
            if (gamepad2.dpad_left) {
                hSlide.endgameSetPosition();
                angleMechanism.endgame_position();
            }
        }
        else if (gamepad2.cross) {
            // angleMechanism.min_position();
            angleMechanism.lowered_position();

            //angleMechanism.lower();
        }
        else if (gamepad2.circle) {
            angleMechanism.low_mid_position();
        }
        else if (gamepad2.square && hSlide.checkIfSafeToRaise()) {
            angleMechanism.high_mid_position();
        }
//        else if (gamepad2.dpad_left && hSlide.checkIfSafeToRaise()) {
//            //hSlide.setPosition(0.87);
//            angleMechanism.endgame_position();
//        }

        angleMechanism.reportTelemetry();

        // linear slide controls

        if (gamepad2.right_trigger > 0) {
            vSlidePID.raisePosition(200);
            //hSlide.extend();
        }
        else if (gamepad2.left_trigger > 0) {
            vSlidePID.lowerPosition(200);
            //hSlide.retract();
        }

        vSlidePID.reportTelemetry();
 //       vSlide.reportTelemetry();

        hSlide.reportTelemetry();

        //drive train controls: rcm
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        drive.drive(x, y, rx);

        //hang controls
        if (gamepad1.right_trigger > 0) {
            hang.raise(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > 0) {
            hang.lower(gamepad1.left_trigger);
        }
        else {
            hang.stop();
        }

        if(gamepad1.square) {
            claw.setOpenPos();
        }
        else if (gamepad1.circle) {
            claw.setMidOpenPos();
        }
        else if(gamepad1.cross) {
            claw.setClosedPos();
        }

        //horizontal extension
        if (gamepad2.dpad_up && angleMechanism.checkIfSafeForHorizontalExtension()) {
            hSlide.extend();
        }
        else if (gamepad2.dpad_down) {
            hSlide.retract();
        }
        else if (gamepad2.dpad_right) {
            hSlide.reset();
        }


        //automations

        if (gamepad1.dpad_up) {
            Actions.runBlocking(
                    automations.retractFromSubmersible()
            );
        }

        // angle mechanism pid update
        angleMechanism.updatePIDLoop();
        angleMechanism.reportTelemetry();

    }

    @Override
    public void stop() {
        telemetry.addLine("STOP() CALLED");
        vSlidePID.stop();
        vSlidePID.disable_brake();
        hang.stop();
        hang.disable_brake();
    }
}
