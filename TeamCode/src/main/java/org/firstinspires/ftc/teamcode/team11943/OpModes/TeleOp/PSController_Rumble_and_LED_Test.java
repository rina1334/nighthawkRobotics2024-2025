package org.firstinspires.ftc.teamcode.team11943.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@Disabled
@TeleOp
public class PSController_Rumble_and_LED_Test extends OpMode {
    Gamepad.RumbleEffect rumbleEffect;
    Gamepad.LedEffect ledEffect;
    Gamepad.LedEffect flashingBlue;

    @Override
    public void init() {
        rumbleEffect = new Gamepad.RumbleEffect.Builder()
                .addStep(0.0, 1.0, 500) // rumble right motor 100% for 500 ms
                .addStep(0.0, 0.0, 300) // pause 300 ms
                .addStep(1.0, 0.0, 250) // rumble left motor 100% for 250 ms
                .addStep(0.0, 0.0, 250) // pause 250 ms
                .addStep(1.0, 1.0, 250) // rumble both 250 ms
                .build();

        ledEffect = new Gamepad.LedEffect.Builder()
                .addStep(1, 0, 0, 250)
                .addStep(0, 1, 0, 250)
                .addStep(0, 0, 1, 250)
                .addStep(1, 1, 1, 250)
                .build();

//        flashingBlue = new Gamepad.LedEffect.Builder()
//                .addStep(0, 0, 1, 250)
//                .addStep(0, 0, 0, 250)
//                .addStep(0, 0, 1, 250)
//                .addStep(0, 0, 0, 250)
//                .addStep(0, 0, 1, 250)
//                .addStep(0, 0, 0, 250)
//                .addStep(0, 0, 1, 250)
//                .addStep(0, 0, 0, 250)
//                .addStep(0, 0, 1, 250)
//                .addStep(0, 0, 0, 250)
//                .addStep(0, 0, 1, 250)
//                .addStep(0, 0, 0, 250)
//                .build();

        Gamepad.LedEffect.Builder b = new Gamepad.LedEffect.Builder();
        for (int i = 0; i < 10; ++i) {
            b.addStep(0, 0, 1, 100);
            b.addStep(0, 0, 0, 100);
        }
        flashingBlue = b.build();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            gamepad1.runRumbleEffect(rumbleEffect);
        }
        else if (gamepad1.b) {
            gamepad1.runLedEffect(ledEffect);
        }
        else if (gamepad1.y) {
            gamepad1.setLedColor(1, 0, 1, 5000);
            gamepad1.runRumbleEffect(rumbleEffect);
        }
        else if (gamepad1.x) {
            gamepad1.runLedEffect(flashingBlue);
        }
    }
}
