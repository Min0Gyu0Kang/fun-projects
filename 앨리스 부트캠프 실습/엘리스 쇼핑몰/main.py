import os.path
from flask import Flask, send_from_directory

app = Flask(__name__)

def root_dir():
    return os.path.abspath(os.path.dirname(__file__))

@app.after_request
def add_header(r):
    """
    Disable caching. Source: https://stackoverflow.com/questions/34066804/disabling-caching-in-flask
    Add headers to both force latest IE rendering engine or Chrome Frame,
    and also to cache the rendered page for 10 minutes.
    """
    r.headers["Cache-Control"] = "no-cache, no-store, must-revalidate, public, max-age=0"
    r.headers["Pragma"] = "no-cache"
    r.headers["Expires"] = "0"
    return r

@app.route('/')
@app.route('/<path:path>')
def get_resource(path='index.html'):
    return send_from_directory(root_dir(), path)

app.run('0.0.0.0', port=8080, threaded=True)