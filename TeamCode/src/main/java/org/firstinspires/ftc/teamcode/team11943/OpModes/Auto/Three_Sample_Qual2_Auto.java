package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(group="Isabelle", preselectTeleOp = "QualIntoTheDeep")
public class Three_Sample_Qual2_Auto extends OpMode { ;
    Three_Samples_Auto_Actions actions;

    Vector2d startPos = new Vector2d(0,0);
    double startHeading = Math.toRadians(0);
    Pose2d startPose = new Pose2d(startPos, startHeading);

    @Override
    public void init() {
        actions = new Three_Samples_Auto_Actions(hardwareMap, telemetry);
        //.raised_position();
        Actions.runBlocking(
                actions.raiseAngleMechanism()
        );
    }

    @Override
    public void start() {
        Actions.runBlocking(
                new SequentialAction(

                        //THIS IS CODE THAT WORKS
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
                        actions.pickUpSampleAction()//,

                        //actions.moveFromThirdSampleToBasket(),
                        //actions.depositSampleAction(),
                        //actions.finalDepositSampleAction(),

                        // need to have slide go back down
                        //actions.lowerSlide()



                )
        );
    }

    @Override
    public void loop() {

    }
}
