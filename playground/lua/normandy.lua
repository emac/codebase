local _M = {}

function _M.check(id, redirect)
  -- 检查cookie[empId]和cookie[token]，如果不同时存在，则重定向到Normandy，否则根据empId校验token，校验通过则允许访问，否则重定向到Normandy
  local cookies = ngx.req.get_headers()["Cookie"]
  if cookies == nil then
    ngx.redirect("http://normandy.aihaisi.com/app/"..id.."/login")
  else
    empId = string.match(cookies, "empId=(%d+)")
    token = string.match(cookies, "token=(%d+)")
    if empId == nil or token == nil then
      ngx.redirect("http://normandy.aihaisi.com/app/"..id.."/login")
    else
      local expected = string.sub(math.pow(empId+10000, 2), 2, 8)
      if expected ~= token then
        ngx.redirect("http://normandy.aihaisi.com/app/"..id.."/login")
      end
    end
  end
end

function _M.login(id, redirectUrl)
  -- 检查请求参数empId和token，如果不同时存在则重定向到Normandy，否则将empId和cookie转为cookie跳转到/
  local args = ngx.req.get_uri_args()
  if args["empId"] == nil or args["token"] == nil then
    ngx.redirect("http://normandy.aihaisi.com/app/"..id.."/login")
  else
    ngx.header["Set-Cookie"] = {"empId="..args["empId"]..";path=/", "token="..args["token"]..";path=/"}
    ngx.redirect(redirectUrl)
  end
end

return _M