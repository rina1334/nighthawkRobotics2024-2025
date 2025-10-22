package org.firstinspires.ftc.teamcode.team11943.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.sparkfun_otos_roadrunner.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.team11943.subsystems.AngleMechanism;
import org.firstinspires.ftc.teamcode.team11943.subsystems.Claw;
import org.firstinspires.ftc.teamcode.team11943.subsystems.LinearSlide2;

@Autonomous(group="Isabelle", preselectTeleOp = "Qual1IntoTheDeep")
@Config
public class Auto_OTOS_Basket_Zero_Start extends OpMode {
    SparkFunOTOSDrive drive;
    LinearSlide2 slide;
    AngleMechanism angleMechanism;
    Claw claw;

    Vector2d startPos = new Vector2d(0,0);
    double startHeading = Math.toRadians(0);
    Pose2d startPose = new Pose2d(startPos, startHeading);

    @Override
    public void init() {
        telemetry.addLine("GOING TO BASKET");
        drive = new SparkFunOTOSDrive(hardwareMap, startPose);
        slide = new LinearSlide2(hardwareMap, telemetry);
        angleMechanism = new AngleMechanism(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);
    }

    @Override
    public void start() {

        // set the angle mechanism straight up
        angleMechanism.raised_position();

        Vector2d basketPos = new Vector2d(23.57,-7.85);//24.57 y = -6.85
        double basketHeading = Math.toRadians(34.21);
        Pose2d basketPose = new Pose2d(basketPos, basketHeading);

        Vector2d firstSamplePos = new Vector2d(17.32, -21.12); //17.32, 21.32, 20.32, -22.12
        double firstSampleHeading = Math.toRadians(-90.75);
        Pose2d firstSamplePose = new Pose2d(firstSamplePos, firstSampleHeading);

        Vector2d secondSamplePos = new Vector2d(17.32 + 10.0, -21.12); //-22.12
        double secondSampleHeading = Math.toRadians(-90.75);
        Pose2d secondSamplePose = new Pose2d(secondSamplePos, secondSampleHeading);

        Action moveToBasket = drive.actionBuilder(startPose)
                .setTangent(startHeading - Math.toRadians(45))  // -45 to prevent hitting wall
                .splineToLinearHeading(basketPose, basketPose.heading)
                .build();

        Action moveToFirstSample = drive.actionBuilder(basketPose)
                .setTangent(firstSampleHeading)
                .splineToLinearHeading(firstSamplePose, firstSamplePose.heading)
                .build();

        Action moveFirstSampleToBasket = drive.actionBuilder(firstSamplePose)
                .setTangent(Math.toRadians(45))
                .splineToLinearHeading(basketPose, basketPose.heading)
                .build();

        Action moveToSecondSample = drive.actionBuilder(basketPose)
                .setTangent(secondSampleHeading)
                .splineToLinearHeading(secondSamplePose, secondSamplePose.heading)
                .build();

        Action moveSecondSampleToBasket = drive.actionBuilder(secondSamplePose)
                .setTangent(Math.toRadians(45))
                .splineToLinearHeading(basketPose, basketPose.heading)
                .build();

        Action depositSampleInBasket = drive.actionBuilder(basketPose)
                //.stopAndAdd(angleServo.changeAngleAction(0.2))     // tilt partly towards basket
                .waitSeconds(0.5)
                .stopAndAdd(claw.openClawAction())
                .waitSeconds(0.5)
                //.stopAndAdd(angleServo.changeAngleAction(0.35))
                .waitSeconds(0.5)
                .build();

        Action depositFirstSampleInBasket = drive.actionBuilder(basketPose)
                //made private
                //.stopAndAdd(angleServo.changeAngleAction(0.2))     // tilt partly towards basket
                .waitSeconds(0.5)
                .stopAndAdd(claw.openClawAction())
                .waitSeconds(0.5)
                //made private
                //.stopAndAdd(angleServo.changeAngleAction(0.35))
                .waitSeconds(0.5)
                .build();

        Action depositSecondSampleInBasket = drive.actionBuilder(basketPose)
                //made private
                //.stopAndAdd(angleServo.changeAngleAction(0.25))     // tilt partly towards basket
                .waitSeconds(0.5)
                .stopAndAdd(claw.openClawAction())
                .waitSeconds(0.5)
                // made private
                //.stopAndAdd(angleServo.changeAngleAction(0.35))
                .waitSeconds(0.5)
                .build();


        Action autonomousAction = drive.actionBuilder(startPose)
                // 1. Drive to basket while extending slide
                .stopAndAdd(new ParallelAction(
                        slide.setPositionAction(2000),  // partially raise slide
                        moveToBasket
                ))
                .stopAndAdd(slide.setPositionAction(4400))  // raise slide above basket

                // 2. Deposit pre-loaded sample into basket
                .stopAndAdd(depositSampleInBasket)

                // 3. Lower slide while moving to first sample
                .stopAndAdd(slide.setPositionAction(2000, 0.5))
                .stopAndAdd(new ParallelAction(
                        slide.setPositionAction(0, 0.5),  // raise slide above basket
                        moveToFirstSample
                ))

                // 4. Pick up first sample
                //.stopAndAdd(angleServo.tiltedAngleAction())
                //.waitSeconds(1)
                .stopAndAdd(angleMechanism.loweredAction())
                .waitSeconds(0.5)
                .stopAndAdd(claw.closeClawAction())
                .waitSeconds(0.5)
                .stopAndAdd(angleMechanism.raisedAction())

                // 5. Move with first sample to Basket
                .stopAndAdd(new ParallelAction(
                        slide.setPositionAction(2000),
                        moveFirstSampleToBasket
                ))
                .stopAndAdd(slide.setPositionAction(4400))

                // 6. Deposit first sample in Basket
                .stopAndAdd(depositFirstSampleInBasket)

                // 7. Lower slide while moving to second sample
                .stopAndAdd(slide.setPositionAction(2000, 0.5))
                .stopAndAdd(new ParallelAction(
                        slide.setPositionAction(0, 0.5),  // raise slide above basket
                        moveToSecondSample
                ))

                // 8. Pick up second sample
                //.stopAndAdd(angleMechanism.tiltedAngleAction())
               // .waitSeconds(1)
                .stopAndAdd(angleMechanism.loweredAction())
                .waitSeconds(0.5)
                .stopAndAdd(claw.closeClawAction())
                .waitSeconds(0.5)
                .stopAndAdd(angleMechanism.raisedAction())

                // 9. Move second sample to Basket
                .stopAndAdd(new ParallelAction(
                        slide.setPositionAction(2000),
                        moveSecondSampleToBasket
                ))
                .stopAndAdd(slide.setPositionAction(4400))

                // 10. Deposit second sample in basket
                .stopAndAdd(depositSecondSampleInBasket)

                // 11. Lower slide while moving to XXXXXXX
                .stopAndAdd(slide.setPositionAction(2000, 0.5))
                .stopAndAdd(slide.setPositionAction(200, 0.5))
                .build();

        Actions.runBlocking(
                autonomousAction
        );
    }

    @Override
    public void loop() {
        slide.reportTelemetry();
        claw.reportTelemetry();
        drive.updatePoseEstimate();
        telemetry.addData("x", drive.pose.position.x);
        telemetry.addData("y", drive.pose.position.y);
        telemetry.addData("heading (deg)", Math.toDegrees(drive.pose.heading.toDouble()));
    }

}
