from flask import Flask, request

app = Flask(__name__)
url = None


# @app.route("/")
# def hello123():
# 	return "xd"


@app.route("/", methods=["GET"])
def hello1():
	from domofond_parser import get_data_by_link
	data = get_data_by_link(url)
	print(data)
	from new_tens import getDataFromReadyNeural
	per = getDataFromReadyNeural(data)
	print(per)
	return str(per)
	# return url


@app.route('/', methods=["POST"])
def doit():
	global url
	request_data = request.form.to_dict()
	for k, v in request_data.items():
		url = k
	# print(url)


app.run(port=3000)
