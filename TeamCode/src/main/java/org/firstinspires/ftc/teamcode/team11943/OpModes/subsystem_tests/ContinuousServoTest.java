package org.firstinspires.ftc.teamcode.team11943.OpModes.subsystem_tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide;

@TeleOp(group="Isabelle")
public class ContinuousServoTest extends OpMode {

    private CRServo ContinuousArmServo;

    @Override
    public void init() {
        ContinuousArmServo = hardwareMap.crservo.get("ContinuousArmServo");
    }

    @Override
    public void loop(){
        if (gamepad1.right_trigger > 0) {
            ElapsedTime timer = new ElapsedTime();
            timer.reset();
            while (timer.milliseconds() < 4000) {
                ContinuousArmServo.setPower(1);
            }
        }
        else if (gamepad1.left_trigger > 0){
            ElapsedTime timer = new ElapsedTime();
            timer.reset();
            while(timer.milliseconds() < 4000){
                ContinuousArmServo.setPower(-1);
            }
        }
            ContinuousArmServo.setPower(0);
        }
    }