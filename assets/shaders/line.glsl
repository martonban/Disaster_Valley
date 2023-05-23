#type vertex
#version 330 core
layout (location=5) in vec2 position;
layout (location=6) in vec4 color;

uniform mat4 uProjection;
uniform mat4 uView;

out vec4 fragColor;

void main()
{
    fragColor = color;
    gl_Position = uProjection * uView * vec4(position, 0.0f, 1.0f);
}

#type fragment
#version 330 core

in vec4 fragColor;
out vec4 outColor;

void main()
{
    outColor = fragColor;
}