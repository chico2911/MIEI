using Newtonsoft.Json.Linq;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Server.Controllers
{
    public class CommentsController : ApiController
    {


        // POST: api/Comments
        public JObject Post([FromBody] Comentario comments)
        {
            JObject jsons = new JObject();
            Jwtmodel jwt = new Jwtmodel();
            int valtok = jwt.jwtCheck(comments.Token);

            if (valtok == 1)
            {
                BD bd = new BD();

                string token = jwt.jwtcreate();
                jsons["valido"] = JToken.FromObject("1");
                jsons["token"] = JToken.FromObject(token);
                jsons["cenas"] = JToken.FromObject(bd.getcomments(comments.id, comments.tipo));
                return jsons;
            }
            else
            {
                jsons["valido"] = JToken.FromObject(0);
                return jsons;
            }
        }

    }
}
