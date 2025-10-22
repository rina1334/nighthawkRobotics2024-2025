package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;

public class Three_Samples_Auto_Actions {
    AngleMechanism angleMechanism;
    Claw claw;
    SparkFunOTOSDrive drive;
    LinearSlide2 slide;

    Vector2d startPos = new Vector2d(0,0);
    double startHeading = Math.toRadians(0);
    Pose2d startPose = new Pose2d(startPos, startHeading);

    Vector2d firstSamplePos = new Vector2d(17.32 + 3, -21.12 + 4); //17.32, 21.32, 20.32, -22.12
    double firstSampleHeading = Math.toRadians(-90.75);
    Pose2d firstSamplePose = new Pose2d(firstSamplePos, firstSampleHeading);

    Vector2d secondSamplePos = new Vector2d(17.32 + 10.0 + 3, -21.12 + 4); //-22.12
    double secondSampleHeading = Math.toRadians(-90.75);
    Pose2d secondSamplePose = new Pose2d(secondSamplePos, secondSampleHeading);

    Vector2d thirdSamplePos = new Vector2d(24.23, -24.69+2);
    double thirdSampleHeading = Math.toRadians(-46.65);
    Pose2d thirdSamplePose = new Pose2d(thirdSamplePos, thirdSampleHeading);

    Vector2d basketPos = new Vector2d(23.57,-7.85);//24.57 y = -6.85
    double basketHeading = Math.toRadians(34.21);
    Pose2d basketPose = new Pose2d(basketPos, basketHeading);

    public Three_Samples_Auto_Actions(HardwareMap hardwareMap, Telemetry tele) {
        angleMechanism = new AngleMechanism(hardwareMap, tele);
        claw = new Claw(hardwareMap, tele);
        drive = new SparkFunOTOSDrive(hardwareMap, startPose);
        slide = new LinearSlide2(hardwareMap, tele);
    }

    public Action raiseAngleMechanism() {
        return angleMechanism.raisedAction();
    }

    public Action moveFromStartToBasket() {
        return drive.actionBuilder(drive.pose)
                .setTangent(startHeading - Math.toRadians(45))  // -45 to prevent hitting wall
                .splineToLinearHeading(basketPose, basketPose.heading)
                .build();
    }

    public Action raiseSlideWhileMovingFromStartToBasket() {
        return new ParallelAction(
                this.moveFromStartToBasket(),
                slide.midSlideAction()
        );
    }

    public Action moveFromFirstSampleToBasket() {
        return new ParallelAction(
                this.moveToBasketFrom(firstSamplePose),
                slide.midSlideAction()
        );
    }

    public Action moveFromSecondSampleToBasket() {
        return new ParallelAction(
                this.moveToBasketFrom(secondSamplePose),
                slide.midSlideAction()
        );
    }

    public Action moveFromThirdSampleToBasket() {
        return new ParallelAction(
                this.moveToBasketFrom(thirdSamplePose),
                slide.midSlideAction()
        );
    }

    public Action moveToBasketFrom(Pose2d currentPose) {
        return drive.actionBuilder(currentPose)
                .setTangent(Math.toRadians(45))
                .splineToLinearHeading(basketPose, basketPose.heading)
                .build();
    }

    public Action moveFromBasketToFirstSample() {
        return new SequentialAction(
                slide.midSlideAction(),
                new ParallelAction(
                        slide.lowerSlideAction(),
                        this.moveFromBasketTo(firstSamplePose)
                )
        );
    }

    public Action moveFromBasketToSecondSample() {
        return new SequentialAction(
                slide.midSlideAction(),
                new ParallelAction(
                        slide.lowerSlideAction(),
                        this.moveFromBasketTo(secondSamplePose)
                )
        );

    }

    public Action moveFromBasketToThirdSample() {
        return new SequentialAction(
                slide.midSlideAction(),
                new ParallelAction(
                        slide.lowerSlideAction(),
                        this.moveFromBasketTo(thirdSamplePose)
                )
        );
    }

    public Action lowerSlide() {
        return slide.lowerSlideAction();
    }

//    public Action moveWhileRaisingSlideAction() {
//        return new ParallelAction(
//                slide.raiseSlideAction(),
//                this.moveToBasketAction()
//        );
//    }

    public Action moveFromBasketTo(Pose2d newPose) {
        return drive.actionBuilder(basketPose)
                .setTangent(newPose.heading)
                .splineToLinearHeading(newPose, newPose.heading)
                .build();
    }

    public Action pickUpSampleAction() {
        return new SequentialAction(
                claw.openClawAction(),
                angleMechanism.loweredAction(),
                //new SleepAction(0.25),
                claw.closeClawAction(),
                new SleepAction(0.25),
                angleMechanism.raisedAction()
        );
    }

    public Action depositSampleAction() {
        return new SequentialAction(
               slide.raiseSlideAction(),
               //new SleepAction(2),
               angleMechanism.highPartiallyRaisedAction(),
               //new SleepAction(0.25),
               claw.openClawAction(),
               new SleepAction(0.25),
               angleMechanism.raisedAction()
        );
    }

    public Action finalDepositSampleAction() {
        return new SequentialAction(
                slide.midSlideAction(),
                //new SleepAction(2),
                angleMechanism.highPartiallyRaisedAction(),
                //new SleepAction(0.25),
                claw.openClawAction(),
                new SleepAction(0.25),
                angleMechanism.raisedAction()
        );
    }


}
