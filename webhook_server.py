from flask import Flask, request
import subprocess
import datetime

app = Flask(__name__)
SECRET_TOKEN = "k8s-123123"

@app.route('/webhook', methods=['POST'])
def auto_deploy():
    # 1. 验证 Token
    token = request.args.get('token')
    if token != SECRET_TOKEN:
        return {"error": "Unauthorized"}, 401

    # 2. 获取参数：是 frontend 还是 backend？
    service_type = request.args.get('type') 
    
    print(f"[{datetime.datetime.now()}] 收到构建通知，业务类型: {service_type}")

    kube_cmd = "KUBECONFIG=/home/dja/.kube/config /usr/local/bin/kubectl"

    if service_type == 'backend':
        cmd = f"{kube_cmd} rollout restart deployment java-backend"
    elif service_type == 'frontend':
        cmd = f"{kube_cmd} rollout restart deployment vue-frontend"
    else:
        return {"error": "Unknown type"}, 400

    # 3. 执行重启命令
    try:
        output = subprocess.check_output(cmd, shell=True).decode('utf-8')
        return {"status": "success", "message": output}, 200
    except Exception as e:
        return {"status": "error", "message": str(e)}, 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)