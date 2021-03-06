package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class FoundationClaw {
    private Servo servoA;
    private Servo servoB;

    //servos in same orientation I think
    private final double pushPositionA = 0.85;
    private final double pushPositionB = 0.15;
    private final double restPositionA = 0.0;
    private final double restPositionB = 1.0;
    public FoundationClaw(Servo a, Servo b) {
        this.servoA = a;
        this.servoB = b;
        servoA.setPosition(restPositionA);
        servoB.setPosition(restPositionB);
    }

    public void controls(Gamepad gp) {
        if(gp.dpad_down) {
            push();
        }

        else if(gp.dpad_up) {
            rest();
        }
    }

    public synchronized void push() {
        servoA.setPosition(pushPositionA);
        servoB.setPosition(pushPositionB);
    }

    public synchronized void rest() {
        servoA.setPosition(restPositionA);
        servoB.setPosition(restPositionB);
    }




    public void pushNoSync() {
        servoA.setPosition(pushPositionA);
        servoB.setPosition(pushPositionB);
    }

    public void restNoSync() {
        servoA.setPosition(restPositionA);
        servoB.setPosition(restPositionB);
    }
}
