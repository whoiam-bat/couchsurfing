import cv2
import base64
import numpy as np
from readmrz import MrzDetector, MrzReader
import model.verification_request


def decode_image(source_image):
    decoded = base64.b64decode(source_image.split('base64,')[1])
    nparr = np.frombuffer(decoded, np.uint8)
    img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    return img


def process_image(image):
    detector = MrzDetector()
    reader = MrzReader()

    resized = detector.resize(image)
    smoothed = detector.smooth(resized)
    dark = detector.find_dark_regions(smoothed)
    thresh = detector.apply_threshold(dark)

    y, y1, x, x1 = detector.find_coordinates(thresh, smoothed)
    print(y, y1, x, x1)

    w = x1 - x
    h = y1 - y

    code = reader.read_mrz(resized[y:y1, x:x1])

    return reader.get_fields(code)


def verify(request: model.verification_request.VerificationData):
    fullname = request.fullname.upper()

    image = decode_image(request.passport_img)
    mrz_data = process_image(image)

    print(mrz_data)

    name = mrz_data.get('name')
    surname = mrz_data.get('surname')

    return name in fullname and surname in fullname