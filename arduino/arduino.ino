/**
 * Created by alexandrebrouste on 02/03/2018.
 */

#include "I2Cdev.h"
#include "MPU6050_6Axis_MotionApps20.h"
#include "Wire.h"

MPU6050 mpu;

// ultrasons variables
long duration;
int distance;

// MPU control/status vars
uint16_t packetSize;    
uint16_t fifoCount;    
uint8_t fifoBuffer[64]; 

// orientation/motion vars
Quaternion q;           // [w, x, y, z]         quaternion container
VectorFloat gravity;    // [x, y, z]            gravity vector
float ypr[3];           // [yaw, pitch, roll]   yaw/pitch/roll container

// defines pins numbers
const int trigPin = 3;
const int echoPin = 2;
const int btnPin = 6;

void setup() {
    Wire.begin();
    Wire.setClock(400000);

    Serial.begin(9600);
    while (!Serial);

    // initialize device
    mpu.initialize();

    // load and configure the DMP
    mpu.dmpInitialize();

    // gyro/accelero offsets
    mpu.setXGyroOffset(220);
    mpu.setYGyroOffset(76);
    mpu.setZGyroOffset(-85);
    mpu.setZAccelOffset(1788);

    // turn on the DMP, now that it's ready
    mpu.setDMPEnabled(true);

    // get expected DMP packet size for later comparison
    packetSize = mpu.dmpGetFIFOPacketSize();

    // configure LED for output
    pinMode(trigPin, OUTPUT);
    pinMode(echoPin, INPUT);
    pinMode(btnPin, INPUT_PULLUP); // emulate the proximity sensor
}

void loop() {
    // --- ultrasons ---
    
    // Clears the trigPin
    digitalWrite(trigPin, LOW);
    delayMicroseconds(2);
    
    // Sets the trigPin on HIGH state for 10 micro seconds
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPin, LOW);
    
    // Reads the echoPin, returns the sound wave travel time in microseconds
    duration = pulseIn(echoPin, HIGH, 10000);
    
    // Calculating the distance
    distance = duration * 0.034 / 2;
    if (distance == 0)
        distance = 170;
        
    // Prints the distance on the Serial Monitor
    Serial.print("alt:");
    Serial.println(distance);


    // --- proximity ---
    
    if(digitalRead(btnPin) == LOW)
      Serial.println("alert");


    // --- Inertial measurement unit ---
    
    // get current FIFO count
    fifoCount = mpu.getFIFOCount();
    
    while (fifoCount < packetSize) fifoCount = mpu.getFIFOCount();

    mpu.getFIFOBytes(fifoBuffer, packetSize);
    fifoCount -= packetSize;
    
    // display YPR angles in degrees
    mpu.dmpGetQuaternion(&q, fifoBuffer);
    mpu.dmpGetGravity(&gravity, &q);
    mpu.dmpGetYawPitchRoll(ypr, &q, &gravity);
    Serial.print("ypr:");
    Serial.print(ypr[0] * 180/M_PI);
    Serial.print(",");
    Serial.print(ypr[1] * 180/M_PI);
    Serial.print(",");
    Serial.println(ypr[2] * 180/M_PI);
}

