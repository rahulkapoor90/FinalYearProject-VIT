#include <dht.h>
#define dht_dpin A3
int val;
int tempPin = 1;
int buzzer = 10;
int smokeA0 = A5;
int water = A2;
dht DHT;
int sensorThres = 400;

void setup()
{
  pinMode(buzzer, OUTPUT);
  pinMode(smokeA0, INPUT);
  Serial.begin(9600);
}
void loop()
{
int analogSensor = analogRead(smokeA0);
int waterSensor = analogRead(water);

 DHT.read11(dht_dpin);

    Serial.print("Current humidity = ");
    Serial.print(DHT.humidity);
    Serial.print("%  ");
    Serial.print("temperature = ");
    Serial.print(DHT.temperature); 
    Serial.println("C  ");


 Serial.print("Wwater ");
  Serial.println(waterSensor);
  Serial.print("Pin A0: ");
  Serial.println(analogSensor);
  // Checks if it has reached the threshold value
  if (analogSensor > sensorThres)
  {
    
    tone(buzzer, 1000, 200);
  }
  else
  {
 
    noTone(buzzer);
  }
  
  
val = analogRead(tempPin);
float mv = ( val/1024.0)*5000; 
float cel = mv/10;
float farh = (cel*9)/5 + 32;

Serial.print("TEMPRATURE = ");
Serial.print(cel);
Serial.print("*C");
Serial.println();


delay(1000);

/* uncomment this to get temperature in farenhite 
Serial.print("TEMPRATURE = ");
Serial.print(farh);
Serial.print("*F");
Serial.println();


*/
}

