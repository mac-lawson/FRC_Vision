package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
/**
 * A multi-functional Vision System for FRC using PhotonVision
 * @author Mac Lawson
 * @see frc.robot.Vision.getAngleAndDistance()
 * In Robot.java, call getAngleAndDistance()
 */
public class Vision {
    // regular remote-eyes
    // camera positioning info
    final static double CAMERA_HEIGHT_METERS = Units.inchesToMeters(24);
    final static double TARGET_HEIGHT_METERS = Units.feetToMeters(5);
    final static double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);

    // target info
    final static double GOAL_RANGE_METERS = Units.feetToMeters(3);

    static PhotonCamera camera = new PhotonCamera("example");

    /**
     * If enabled, sends the target angle and distance to Shuffleboard
     * @param sendAngle
     * @param sendRange
     */
    public static void getAngleAndDistance(boolean sendAngle, boolean sendRange) {
            var result = camera.getLatestResult();

            if (result.hasTargets()) {
                double range =
                        PhotonUtils.calculateDistanceToTargetMeters(
                                CAMERA_HEIGHT_METERS,
                                TARGET_HEIGHT_METERS,
                                CAMERA_PITCH_RADIANS,
                                Units.degreesToRadians(result.getBestTarget().getPitch()));


                double targetAnglePosition = result.getBestTarget().getYaw();
                double targetPositionDistance = range;
                if(sendAngle == true) {
                    SmartDashboard.putNumber("Target Angle Position", targetAnglePosition);

                }
                if(sendRange == true) {
                    SmartDashboard.putNumber("Target Range", targetPositionDistance);
                }
            }
    }

    /**
     * When called, begins a capture of a vision feed to a specific port
     * @param CAMERA_NAME
     * @param PORT
     */
    public static void VideoCapture(UsbCamera CAMERA_NAME, Integer PORT) {
        CAMERA_NAME = CameraServer.startAutomaticCapture(PORT);
    }


    

    
}
