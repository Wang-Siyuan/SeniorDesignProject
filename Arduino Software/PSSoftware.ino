
void setup()
{
  pinMode(13,OUTPUT);
  //digitalWrite(13,HIGH);
  pinMode(7, OUTPUT);
  Serial.begin(57600);           // start serial for output
  Serial.println("Program Started");
}

void loop()
{
 int inByte;
  inByte=Serial.read();
  if(inByte == 'a')
  {
    digitalWrite(13,HIGH);
    digitalWrite(7,HIGH);
  }else if(inByte == 'b')
  {
     digitalWrite(13,LOW);
     digitalWrite(7,LOW);
  }else if(inByte == 'd')
  {
     Serial.print("Ardui");
  }
  delay(1000);
}

