from ultralytics import YOLO

FILE = "mil"

model = YOLO(FILE + ".pt")
model.export(format="onnx",imgsz=[640,640], opset=12)  # export the model to ONNX format

import os

os.system(f" onnx-tf convert -i {FILE}.onnx -o tfmodel/")


import tensorflow as tf

converter = tf.lite.TFLiteConverter.from_saved_model('tfmodel/')
tflite_model = converter.convert()

with open(f'tfmodel/{FILE}_model.tflite', 'wb') as f:
    f.write(tflite_model)