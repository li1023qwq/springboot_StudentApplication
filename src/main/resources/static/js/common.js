// 显示加载动画
function showLoading() {
    if (!$('.loading-overlay').length) {
        $('body').append(`
            <div class="loading-overlay">
                <div class="loading-spinner"></div>
            </div>
        `);
    }
    $('.loading-overlay').show();
}

// 隐藏加载动画
function hideLoading() {
    $('.loading-overlay').hide();
}

// 显示提示消息
function showMessage(message, type = 'success') {
    const toast = $(`
        <div class="toast ${type}">
            ${message}
        </div>
    `).appendTo('body');
    
    setTimeout(() => {
        toast.fadeOut(() => toast.remove());
    }, 3000);
}

// 确认对话框
function confirm(message) {
    return new Promise((resolve) => {
        const modal = $(`
            <div class="confirm-modal">
                <div class="confirm-content">
                    <p>${message}</p>
                    <div class="confirm-buttons">
                        <button class="btn" onclick="$(this).closest('.confirm-modal').remove(); resolve(false)">取消</button>
                        <button class="btn btn-primary" onclick="$(this).closest('.confirm-modal').remove(); resolve(true)">确定</button>
                    </div>
                </div>
            </div>
        `).appendTo('body');
        
        modal.click(function(e) {
            if ($(e.target).is('.confirm-modal')) {
                modal.remove();
                resolve(false);
            }
        });
    });
}

// 检查登录状态
function checkLogin() {
    const username = localStorage.getItem('username');
    if (!username) {
        location.href = 'login.html';
        return false;
    }
    // 设置导航栏用户名
    $('#nav-username').text(username);
    return true;
}

// 退出登录
function logout() {
    localStorage.removeItem('username');
    location.href = 'login.html';
}

// AJAX请求封装
function request(url, options = {}) {
    showLoading();
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'http://localhost:8080' + url,
            ...options,
            success: resolve,
            error: reject,
            complete: hideLoading
        });
    });
}

// 格式化日期
function formatDate(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    });
}

// 确认对话框
function confirm(message) {
    return new Promise((resolve) => {
        const result = window.confirm(message);
        resolve(result);
    });
}

// AJAX请求封装
const http = {
    get: function(url) {
        return $.get(url);
    },
    
    post: function(url, data) {
        return $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data)
        });
    },
    
    delete: function(url) {
        return $.ajax({
            url: url,
            type: 'DELETE'
        });
    }
}; 