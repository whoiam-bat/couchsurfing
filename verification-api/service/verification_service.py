import cv2
import easyocr
import base64
import numpy as np
import model.verification_request


def decode_image(source_image):
    decoded = base64.b64decode(source_image)
    nparr = np.frombuffer(decoded, np.uint8)
    img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)

    return img


def process_image(image):
    ksize = 3
    image = cv2.GaussianBlur(image, (ksize, ksize), 0)

    image = cv2.threshold(image, 150, 255, cv2.THRESH_BINARY)[1]

    reader = easyocr.Reader(['en'], gpu=False)
    result = reader.readtext(image)
    return result


def verify(request: model.verification_request.VerificationData):
    firstname, lastname = request.fullname.split(' ')
    image_base64 = request.passport_img

    image = decode_image(image_base64)
    img_data = process_image(image)

    if len(img_data) < 4:
        return False

    mrz = retrieve_data_from_mrz(img_data[-4])

    return str.upper(firstname) in mrz and str.upper(lastname) in mrz


def retrieve_data_from_mrz(mrz):
    return ''.join(mrz[1].split(' '))


