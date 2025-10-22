package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.HorizontalExtension;

@Autonomous(group="Isabelle", preselectTeleOp = "FinalSuperQualIntoTheDeep")
public class Four_Sample_Qual2_Auto extends OpMode { ;
    Four_Sample_Auto_Actions actions;

    Vector2d startPos = new Vector2d(0,0);
    double startHeading = Math.toRadians(0);
    Pose2d startPose = new Pose2d(startPos, startHeading);


    @Override
    public void init() {
        actions = new Four_Sample_Auto_Actions(hardwareMap, telemetry);
        //.raised_position();
        Actions.runBlocking(
                new SequentialAction(
                        actions.raiseAngleMechanism()
                )
        );

    }

    @Override
    public void start() {
        Actions.runBlocking(
                new SequentialAction(
                        // actions.raiseAngleMechanism(),
                        actions.raiseSlideWhileMovingFromStartToBasket(),
                        actions.depositSampleAction(),

                        actions.moveFromBasketToFirstSample(),
                        actions.pickUpSampleAction(),
                        actions.moveFromFirstSampleToBasket(),
                        actions.depositSampleAction(),

                        actions.moveFromBasketToSecondSample(),
                        actions.pickUpSampleAction(),
                        actions.moveFromSecondSampleToBasket(),
                        actions.depositSampleAction(),

                        actions.moveFromBasketToThirdSample(),
                        //actions.pickUpSampleAction(),
                        actions.pickUpThirdSampleAction(),
                        actions.moveFromThirdSampleToBasket(),
                        actions.depositSampleAction(),

                        // need to have slide go back down
                        actions.lowerSlide()

                )
        );
    }

    @Override
    public void loop() {

    }
}
